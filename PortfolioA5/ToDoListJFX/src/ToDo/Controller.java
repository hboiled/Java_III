package ToDo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.*;

import javax.swing.*;
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
    void AddBtnClick(MouseEvent event) {
        add();
    }

    @FXML
    void Submit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            add();
            event.consume();
        }
    }

    @FXML
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
    void ClearAll(MouseEvent event) {
        TodayToDo.getItems().clear();
        TaskList.getItems().clear();
        event.consume();
    }

    @FXML
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
    void CrossOff(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Task selTask = TodayToDo.getSelectionModel().getSelectedItem();
            if (selTask.isComplete()) {
                selTask.incomplete();
            } else {
                selTask.markComplete();
            }
            init();
            System.out.println(selTask + " " + selTask.isComplete());
        }
    }

    @FXML
    void DragTask(MouseEvent event) {
        Task task = TaskList.getSelectionModel().getSelectedItem();
        if (task == null) {
            event.consume();
            return;
        }
        Node cell = TaskList.getChildrenUnmodifiable().get(0);

        cell.setOnDragDetected(mouseEvent -> {
            Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(task.toString());
            db.setContent(content);

            mouseEvent.consume();
        });

        TodayToDo.setOnDragOver(dragEvent -> {

            if (dragEvent.getGestureSource() != TodayToDo
                    && dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        TodayToDo.setOnDragEntered(dragEvent -> {
            if (dragEvent.getGestureSource() != TodayToDo
                    && dragEvent.getDragboard().hasString()) {
            }
            dragEvent.consume();
        });

        // remove?
        TodayToDo.setOnDragExited(dragEvent -> {
            System.out.println("drag exited");

            dragEvent.consume();
        });

        TodayToDo.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                TodayToDo.getItems().add(new Task(db.getString()));
                success = true;
                db.clear();
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

        cell.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                TaskList.getItems().remove(task);
            }
        });
    }

    @FXML
    void DragBack(MouseEvent event) {
        Task task = TodayToDo.getSelectionModel().getSelectedItem();
        if (task == null) {
            event.consume();
            return;
        }

        Node cell = TodayToDo.getChildrenUnmodifiable().get(0);

        cell.setOnDragDetected(mouseEvent -> {
            Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(task.toString());
            db.setContent(content);

            mouseEvent.consume();
        });


        TaskList.setOnDragOver(dragEvent -> {

            if (dragEvent.getGestureSource() != TaskList
                    && dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        TaskList.setOnDragEntered(dragEvent -> {
            if (dragEvent.getGestureSource() != TaskList
                    && dragEvent.getDragboard().hasString()) {
            }
            dragEvent.consume();
        });

        TaskList.setOnDragExited(dragEvent -> {
            System.out.println("drag exited");

            dragEvent.consume();
        });

        TaskList.setOnDragDropped(dragEvent -> {
            System.out.println("dropped " + task);
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                TaskList.getItems().add(new Task(db.getString()));
                success = true;
                db.clear();
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

        cell.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                TodayToDo.getItems().remove(task);
            }
        });
    }

    private void add() {
        if (!textInput.getText().isBlank()) {
            Task newTask = new Task(textInput.getText());
            TaskList.getItems().add(newTask);
        }
        textInput.clear();
    }

    private void init() {
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
    void QuitApp(ActionEvent event) {
        System.exit(0);
    }

}
