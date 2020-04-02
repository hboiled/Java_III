package ToDo;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.text.Text;


public class Controller {

    @FXML
    private TextField textInput;

    @FXML
    private Text DisplayItem;

    @FXML
    private ListView<String> TaskList = new ListView<>();

    @FXML
    private ListView<String> TodayToDo = new ListView<>();

    @FXML
    private Button AddBtn;

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
    void DragTask(MouseEvent event) {
        // have to reclick to set drag target
        String task = TaskList.getSelectionModel().getSelectedItem();
        if (task == null) {
            event.consume();
            return;
        }
        System.out.println(task);
        int index = TaskList.getSelectionModel().getSelectedIndex();
        // returns only selected
        System.out.println(TaskList.getChildrenUnmodifiable().size());
        System.out.println(index);
        Node cell = TaskList.getChildrenUnmodifiable().get(0);
        DisplayItem.setText(task);

        cell.setOnDragDetected(mouseEvent -> {
            Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(task);
            db.setContent(content);

            System.out.println("Dragging " + task);
            mouseEvent.consume();
        });

        TodayToDo.setOnDragOver(dragEvent -> {
            //System.out.println("draggin over " + task);

            if (dragEvent.getGestureSource() != TodayToDo
                    && dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        TodayToDo.setOnDragEntered(dragEvent -> {
            if (dragEvent.getGestureSource() != TodayToDo
                    && dragEvent.getDragboard().hasString()) {
                //TaskList.getItems().remove(index);
                System.out.println("left tasklist");
            }
            dragEvent.consume();
        });

        // modify to tasklist change cursor to cell element
        TodayToDo.setOnDragExited(dragEvent -> {
            System.out.println("drag exited");

            dragEvent.consume();
        });

        TodayToDo.setOnDragDropped(dragEvent -> {
            System.out.println("dropped " + task);
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                TodayToDo.getItems().add(db.getString());
                success = true;
                db.clear();
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

        cell.setOnDragDone(dragEvent -> {
            System.out.println("drag done: " + task);
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                TaskList.getItems().remove(task);
            }
        });
    }

    @FXML
    void DragBack(MouseEvent event) {
        String task = TodayToDo.getSelectionModel().getSelectedItem();
        if (task == null) {
            event.consume();
            return;
        }
        System.out.println(task);
        int index = TodayToDo.getSelectionModel().getSelectedIndex();
        // returns only selected
        System.out.println(TodayToDo.getChildrenUnmodifiable().size());
        System.out.println(index);
        Node cell = TodayToDo.getChildrenUnmodifiable().get(0);
        DisplayItem.setText(task);

        cell.setOnDragDetected(mouseEvent -> {
            Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(task);
            db.setContent(content);

            System.out.println("Dragging " + task);
            mouseEvent.consume();
        });


        TaskList.setOnDragOver(dragEvent -> {
            //System.out.println("draggin over " + task);

            if (dragEvent.getGestureSource() != TaskList
                    && dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        TaskList.setOnDragEntered(dragEvent -> {
            if (dragEvent.getGestureSource() != TaskList
                    && dragEvent.getDragboard().hasString()) {
                //TaskList.getItems().remove(index);
                System.out.println("left tasklist");
            }
            dragEvent.consume();
        });

        // modify to tasklist change cursor to cell element
        TaskList.setOnDragExited(dragEvent -> {
            System.out.println("drag exited");

            dragEvent.consume();
        });

        TaskList.setOnDragDropped(dragEvent -> {
            System.out.println("dropped " + task);
            Dragboard db = dragEvent.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                TaskList.getItems().add(db.getString());
                success = true;
                db.clear();
            }
            dragEvent.setDropCompleted(success);
            dragEvent.consume();
        });

        cell.setOnDragDone(dragEvent -> {
            System.out.println("drag done: " + task);
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                TodayToDo.getItems().remove(task);
            }
        });
    }

    private void add() {
        if (!textInput.getText().isBlank()) {
            TaskList.getItems().add(textInput.getText());
        }
        textInput.clear();
    }

}
