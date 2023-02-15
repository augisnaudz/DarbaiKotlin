package com.example.augustino.fxcontrol;

import com.example.augustino.DS.Company;
import com.example.augustino.DS.Student;
import com.example.augustino.HelloApplication;
import com.example.augustino.HibBontrol.UserHib;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.augustino.fxcontrol.LoginCourse.alertMessage;

public class SignUp implements Initializable {
    public TextField loginF;
    public PasswordField pswdF;
    public PasswordField pswdK;
    public RadioButton radioLearner;
    public ToggleGroup userType;
    public RadioButton radioGroup;
    public TextField learnerNameF;
    public TextField learnerSurnameF;
    public TextField learnerEmailF;
    public TextField learnerCardF;
    public TextField groupNameF;
    public TextField groupRepF;
    public TextField groupEmailF;
    public TextField groupAdressF;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SystemCourse");
    UserHib userHib = new UserHib(entityManagerFactory);

    public void createUser(ActionEvent actionEvent) throws IOException {
        if (radioGroup.isSelected()) {
            if(loginF.getText().trim().isEmpty() || pswdF.getText().trim().isEmpty() ||
                    groupNameF.getText().trim().isEmpty() || groupRepF.getText().trim().isEmpty() ||
                    groupEmailF.getText().trim().isEmpty() || groupAdressF.getText().trim().isEmpty()) {
                alertMessage("Užpildykite visus langus");
            }else {
                if(Objects.equals(pswdF.getText(), pswdK.getText())){
                Company company = new Company(loginF.getText(), pswdF.getText(), groupNameF.getText(),
                        groupRepF.getText(), groupEmailF.getText(), groupAdressF.getText());
                userHib.createUser(company);
                alertMessage("Naujas vartotojas sukurtas sekmingai");
                returnToLogin();
                }else{
                    alertMessage("Nesutampa slaptažodžiai");
                }
            }
        } else {
            if(loginF.getText().trim().isEmpty() || pswdF.getText().trim().isEmpty() ||
                    learnerNameF.getText().trim().isEmpty() || learnerSurnameF.getText().trim().isEmpty() ||
                    learnerCardF.getText().trim().isEmpty()){
                alertMessage("Užpildykite visus langus");
            }else{
                if(Objects.equals(pswdF.getText(), pswdK.getText())) {

                    Student student = new Student(loginF.getText(), pswdF.getText(), learnerNameF.getText(),
                            learnerSurnameF.getText(), learnerEmailF.getText(), learnerCardF.getText());
                    userHib.createUser(student);
                    alertMessage("Naujas vartotojas sukurtas sekmingai");
                    returnToLogin();
                }else{
                    alertMessage("Nesutampa slaptažodžiai");
                }
            }
        }
    }

    public void returnToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginCourse.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) pswdF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupAdressF.setDisable(true);
        groupEmailF.setDisable(true);
        groupRepF.setDisable(true);
        groupNameF.setDisable(true);
    }

    public void enableFields(ActionEvent actionEvent) {
        if(radioLearner.isSelected()){
            learnerNameF.setDisable(false);
            learnerEmailF.setDisable(false);
            learnerSurnameF.setDisable(false);
            learnerCardF.setDisable(false);
            groupAdressF.setDisable(true);
            groupEmailF.setDisable(true);
            groupRepF.setDisable(true);
            groupNameF.setDisable(true);
        }else{
            learnerNameF.setDisable(true);
            learnerEmailF.setDisable(true);
            learnerSurnameF.setDisable(true);
            learnerCardF.setDisable(true);
            groupAdressF.setDisable(false);
            groupEmailF.setDisable(false);
            groupRepF.setDisable(false);
            groupNameF.setDisable(false);
        }
    }
}
