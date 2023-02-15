package com.example.augustino.fxcontrol;

import com.example.augustino.DS.User;
import com.example.augustino.HelloApplication;
import com.example.augustino.HibBontrol.UserHib;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class LoginCourse {
    public PasswordField pswdF;
    public TextField loginF;
    public String who;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SystemCourse");
    UserHib userHib= new UserHib(entityManagerFactory);

    public void checkAndLoad(ActionEvent actionEvent) throws IOException {
        User user = userHib.getUserByLoginData(loginF.getText(), pswdF.getText());
        if(user != null){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainCourse.fxml"));
            Parent root = fxmlLoader.load();

            MainCourse mainCourse = fxmlLoader.getController();
            mainCourse.setMainCourse(user.getId());

            Scene scene = new Scene(root);

            Stage stage = (Stage) loginF.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }else{
            alertMessage("Blogai ivesti duomenys");
        }
    }

    public void newUserForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) loginF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public static void alertMessage(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setHeaderText("Pranešimas:");
        alert.setContentText(mess);
        alert.initModality((Modality.APPLICATION_MODAL) );

        alert.showAndWait();
    }
}
