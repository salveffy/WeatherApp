package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main extends Application {

    public Main() throws UnsupportedEncodingException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Weather");
        primaryStage.setScene(new Scene(root, 260, 499));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
