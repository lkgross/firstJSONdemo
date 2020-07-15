package bsu.comp152;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        var loc = getClass().getResource("Main.fxml");
        try {
            root = FXMLLoader.load(loc);
        } catch (IOException e) {
            System.out.println("Can't find FXML file or Controller threw an exception");
        }
        var windowContents = new Scene(root, 300, 400);
        primaryStage.setScene(windowContents);
        primaryStage.setTitle("Web Data");
        primaryStage.show();
    }


}

