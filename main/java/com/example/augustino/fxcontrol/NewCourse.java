package com.example.augustino.fxcontrol;

import com.example.augustino.DS.Course;
import com.example.augustino.DS.User;
import com.example.augustino.HelloApplication;
import com.example.augustino.HibBontrol.CourseHib;
import com.example.augustino.HibBontrol.UserHib;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class NewCourse implements Initializable {
    public TextArea descCourseF;
    public TextField nameCourseF;
    private int userId;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SystemCourse");
    UserHib userHib = new UserHib(entityManagerFactory);
    CourseHib courseHib = new CourseHib(entityManagerFactory);

    public void loadNewCourse(int id, boolean fix, int ProjectId) {
        this.userId = id;

        if(fix){
            Course course = courseHib.getCourseById(ProjectId);
            nameCourseF.setText(course.getTitle());
            descCourseF.setText(course.getDesc());
        }
    }


    public void doIt(ActionEvent actionEvent) throws IOException {
        User user = userHib.getUserById(userId);
        Course course = new Course(nameCourseF.getText(), descCourseF.getText(), Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        course.getRespLearners().add(user);
        user.getMyCourse().add(course);
        userHib.editUser(user);
        cancelXD();
    }

    private void cancelXD() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainCourse.fxml"));
        Parent root = fxmlLoader.load();

        MainCourse mainCourse = fxmlLoader.getController();
        mainCourse.setMainCourse(userId);

        Scene scene = new Scene(root);

        Stage stage = (Stage) nameCourseF.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void cancelIt(ActionEvent actionEvent) throws IOException {
        cancelXD();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
