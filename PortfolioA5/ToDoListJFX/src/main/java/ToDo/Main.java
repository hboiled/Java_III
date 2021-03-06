package ToDo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Todo.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/tdl.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Todo List");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
