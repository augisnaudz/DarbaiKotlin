package com.example.augustino.fxcontrol;

import com.example.augustino.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGiu extends Application {
    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginCourse.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Kursų sistema");
        stage.setScene(scene);
        stage.show();
    }
}
