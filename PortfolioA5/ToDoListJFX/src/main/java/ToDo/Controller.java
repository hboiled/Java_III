package ToDo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    // control elements
    @FXML
    private MenuItem SaveMenu;

    @FXML
    private MenuItem QuitMenu;

    @FXML
    private MenuItem InstructionMenu;

    @FXML
    private TextField textInput;

    @FXML
    private ListView<Task> TaskList = new ListView<>();

    @FXML
    private ListView<Task> TodayToDo = new ListView<>();

    @FXML
    private Button AddBtn;

    @FXML
    private Button LoadBtn;

    @FXML
    private Button ClearAllBtn;

    @FXML
    private Button ClearCompBtn;

    // methods
    @FXML
    // add task to tasklist
    void AddBtnClick(MouseEvent event) {
        add();
    }

    @FXML
    // alternative add to tasklist
    void Submit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            add();
        }
    }

    @FXML
    // save tasks in tasklist as strings
    void SaveList(ActionEvent event) throws IOException {
        try (Writer writer = new FileWriter("tasks.txt", false)) {
            for (Task task : TaskList.getItems()) {
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    // load from .txt file strings and add them to tasklist
    void Load(MouseEvent event) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                TaskList.getItems().add(new Task(line));
            }
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    @FXML
    // enables help menubar to view instructions
    void DisplayInstructions(ActionEvent event) {
        String url = "instructions.html";
        File help = new File(url);
        try {
            Desktop.getDesktop().browse(help.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    // removes all tasks from both listviews
    void ClearAll(MouseEvent event) {
        TodayToDo.getItems().clear();
        TaskList.getItems().clear();
        event.consume();
    }

    @FXML
    // checks todaytodo for tasks which have been marked completed
    void ClearCompleted(MouseEvent event) {
        List<Task> toBeRemoved = new ArrayList<>();
        for (Task t : TodayToDo.getItems()) {
            if (t.isComplete()) {
                toBeRemoved.add(t);
            }
        }
        TodayToDo.getItems().removeAll(toBeRemoved);
    }

    @FXML
    // enables pressing enter on todaytodo task to adjust style
    // by changing the completed status of tasks
    void CrossOff(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            initStyleChange();
            Task selTask = TodayToDo.getSelectionModel().getSelectedItem();
            if (selTask.isComplete()) {
                selTask.incomplete();
            } else {
                selTask.markComplete();
            }
            System.out.println(selTask + " " + selTask.isComplete());
        }
    }

    @FXML
    // enable drag from tasklist to todaytodo
    void DragTask(MouseEvent event) {
        Task task = TaskList.getSelectionModel().getSelectedItem();
        if (task == null) {
            event.consume();
            return;
        }
        // workaround to obtain the list cell node containing the selected task
        Node cell = TaskList.getChildrenUnmodifiable().get(0);

        drag(task, cell, TodayToDo, TaskList);
    }

    @FXML
    // enables drag back from todaytodo to tasklist
    void DragBack(MouseEvent event) {
        Task task = TodayToDo.getSelectionModel().getSelectedItem();
        if (task == null) {
            event.consume();
            return;
        }
        // workaround to obtain the list cell node containing the selected task
        Node cell = TodayToDo.getChildrenUnmodifiable().get(0);

        drag(task, cell, TaskList, TodayToDo);
    }

    // method to create drag events for both listviews
    private void drag(Task task, Node cell, ListView<Task> destinationLV, ListView<Task> targetLV) {
        //Node cell = TodayToDo.getChildrenUnmodifiable().get(0);

        cell.setOnDragDetected(mouseEvent -> {
            Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(task.toString());
            db.setContent(content);

            mouseEvent.consume();
        });

        destinationLV.setOnDragOver(dragEvent -> {

            if (dragEvent.getGestureSource() != destinationLV
                    && dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        destinationLV.setOnDragEntered(dragEvent -> {
            if (dragEvent.getGestureSource() != destinationLV
                    && dragEvent.getDragboard().hasString()) {
            }
            dragEvent.consume();
        });

        destinationLV.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                destinationLV.getItems().add(new Task(db.getString()));
                success = true;
                db.clear();
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

        cell.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                targetLV.getItems().remove(task);
            }
        });
    }

    private void add() {
        // create and add task to tasklist
        if (!textInput.getText().isBlank()) {
            Task newTask = new Task(textInput.getText());
            TaskList.getItems().add(newTask);
        }
        textInput.clear();
    }

    // modifies cells in todaytodo depending on the completed stats of the task contained
    // attaches the style class "active" to tasks marked completed
    // removes it from tasks marked incomplete
    private void initStyleChange() {
        TodayToDo.setCellFactory(cell -> new ListCell<Task>() {
            static final String ACTIVE = "active";

            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    getStyleClass().remove(ACTIVE);
                } else {
                    if (item.isComplete()) {
                        // adds additional idenfitication to completed tasks if focused on
                        setText(item.toString() + " - finished");
                    } else {
                        setText(item.toString());
                    }

                    if (item.isComplete() && !getStyleClass().contains(ACTIVE)) {
                        getStyleClass().add(ACTIVE);
                    } else {
                        getStyleClass().remove(ACTIVE);
                    }
                }
            }
        });
    }

    @FXML
    // used in conjunction with quit in menubar
    void QuitApp(ActionEvent event) {
        System.exit(0);
    }

}
