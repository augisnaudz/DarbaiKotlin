package com.example.augustino.fxcontrol;

import com.example.augustino.DS.Course;
import com.example.augustino.DS.Projects;
import com.example.augustino.DS.User;
import com.example.augustino.HelloApplication;
import com.example.augustino.HibBontrol.CourseHib;
import com.example.augustino.HibBontrol.ProjectHib;
import com.example.augustino.control.DButil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainCourse implements Initializable {
    public TextField newLogin;
    public PasswordField newPswd;
    public PasswordField newpswdC;
    public TextField newLoginC;
    public Button newCourse;
    public Button courseJoin;
    public ListView myCoursesT;
    public Button editCourse;
    public TableColumn colCreated;
    public TableView tabStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colSurname;
    public TableColumn colEmail;
    public Tab tabIgnore;
    public ListView ownedCourses;
    public TreeView ownedProjects;
    public Button createProject;
    public Button editProject;
    public Button deleteProject;
    public TextArea courseInfo;
    public Button delCourse;
    private int userId;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SystemCourse");
    CourseHib courseHib = new CourseHib(entityManagerFactory);
    ProjectHib projectHib = new ProjectHib(entityManagerFactory);
    private ObservableList<ModelTable> data = FXCollections.observableArrayList();

    public void setMainCourse(int id) {

        this.userId = id;
        hideTab();
        loadOwnedCourses();
    }

    public void toLogOff(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginCourse.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) newPswd.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void saveLogin(ActionEvent actionEvent) throws SQLException {
        if(newLogin.getText().trim().isEmpty()) {
            LoginCourse.alertMessage("Prisijungimo vardo laukelis tuščias");
        }else{
            if (Objects.equals(newLoginC.getText(), newLogin.getText())) {
                Connection connection = DButil.connectToDb();
                Statement statement = connection.createStatement();
                String temp = "UPDATE user SET login = '" + newLogin.getText() + "' WHERE id = " + userId;
                String temp1 = "UPDATE user SET dateModified = '" + LocalDate.now() + "' WHERE id = " + userId;
                int rs = statement.executeUpdate(temp);
                int rs1 = statement.executeUpdate(temp1);
                DButil.disconnectFromDb(connection, statement);
                LoginCourse.alertMessage("Prisijungimo vardas pakeistas sekmingai");
            } else {
                LoginCourse.alertMessage("Prisijungimo vardai nesutampa");
            }
        }
    }

    public void savePswd(ActionEvent actionEvent) throws SQLException {
        if(newPswd.getText().trim().isEmpty()) {
            LoginCourse.alertMessage("Prisijungimo vardo laukelis tuščias");
        }else{
            if (Objects.equals(newPswd.getText(), newpswdC.getText())) {
                Connection connection = DButil.connectToDb();
                Statement statement = connection.createStatement();
                String temp = "UPDATE user SET pswd = '" + newPswd.getText() + "' WHERE id = " + userId;
                String temp1 = "UPDATE user SET dateModified = '" + LocalDate.now() + "' WHERE id = " + userId;
                int rs = statement.executeUpdate(temp);
                int rs1 = statement.executeUpdate(temp1);
                DButil.disconnectFromDb(connection, statement);
                LoginCourse.alertMessage("Slaptažodis pakeistas sekmingai");
            } else {
                LoginCourse.alertMessage("Slaptažodžiai nesutampa");
            }
        }
    }

    public void destroy(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginCourse.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) newPswd.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        Connection connection = DButil.connectToDb();
        Statement statement = connection.createStatement();
        String temp = "DELETE FROM user WHERE id = " + userId;
        int rs = statement.executeUpdate(temp);
        DButil.disconnectFromDb(connection, statement);
    }

    private void loadNewCourse(boolean fix, int courseId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewCourse.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        NewCourse newCourse = fxmlLoader.getController();
        newCourse.loadNewCourse(userId, fix, courseId);
        Stage stage = (Stage) newPswd.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void createNewCourse(ActionEvent actionEvent) throws IOException {
        loadNewCourse(false, 0);

    }

    public void joinCourse(ActionEvent actionEvent) throws SQLException {
        Course selectedCourse = CourseHib.getCourseById(Integer.parseInt(myCoursesT.getSelectionModel().getSelectedItem().toString().split("\\ | ")[0]));
        Connection connection = DButil.connectToDb();
        PreparedStatement preparedStatement;
        String insertIn = "INSERT INTO course_user (`myCourse_id`, `respLearners_id`) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(insertIn);
        preparedStatement.setInt(1, selectedCourse.getId());
        preparedStatement.setInt(2, userId);
        preparedStatement.execute();
        DButil.disconnectFromDb(connection, preparedStatement);
        loadOwnedCourses();
    }

    private void hideTab(){
        try {
            Connection connection = DButil.connectToDb();
            Statement statement = connection.createStatement();
            String temp = ("SELECT id FROM user WHERE id = '" + userId +"' AND groupName is not null");
            ResultSet rs = statement.executeQuery(temp);
            int id = 0;
            while(rs.next()){
                id = rs.getInt("id");
            }
            if(id != userId){
                newCourse.setVisible(false);
                editCourse.setVisible(false);
                tabIgnore.setDisable(true);
                courseJoin.setVisible(true);
                createProject.setVisible(false);
                editProject.setVisible(false);
                deleteProject.setVisible(false);
                delCourse.setVisible(false);
                myCoursesT.getItems().clear();
                List<Course> coursesFromDb = courseHib.getAllCourses();
                for (Course c: coursesFromDb){
                    myCoursesT.getItems().add(c.getId() + " | " + c.getTitle());
                }
            }else{
                tabIgnore.setDisable(false);
                newCourse.setVisible(true);
                editCourse.setVisible(true);
                courseJoin.setVisible(false);
                createProject.setVisible(true);
                editProject.setVisible(true);
                deleteProject.setVisible(true);
                delCourse.setVisible(true);
                myCoursesT.getItems().clear();
                List<Course> coursesFromDb = courseHib.getCourseByUserId(userId);
                for (Course c: coursesFromDb){
                    myCoursesT.getItems().add(c.getId() + " | " + c.getTitle());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadOwnedCourses(){
        List<Course> coursesFromDb = courseHib.getCourseByUserId(userId);
        for (Course c: coursesFromDb){
            ownedCourses.getItems().add(c.getId() + " | " + c.getTitle());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = DButil.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM user u WHERE u.groupName is null");
            while(rs.next()){
                data.add(new ModelTable(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        String.valueOf(rs.getDate("dateCreated"))));
            }
            DButil.disconnectFromDb(connection, statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        tabStudent.setItems(data);}

    public void editChosenCourse(ActionEvent actionEvent) throws IOException {
        loadNewCourse(true, Integer.parseInt(
                myCoursesT.getSelectionModel().getSelectedItem().toString().split("\\ | ")[0]));
    }

    public void loadInfo(MouseEvent mouseEvent) {
        Course selectedCourse = (Course) courseHib.getCourseById(Integer.parseInt(
                myCoursesT.getSelectionModel().getSelectedItem().toString().split("\\ | ")[0]));
        courseInfo.setText(selectedCourse.getDesc());

    }

    public void deleteCourse(ActionEvent actionEvent) {
        CourseHib.removeCourse(Integer.parseInt(myCoursesT.getSelectionModel().getSelectedItem().toString().split("\\ | ")[0]));
        hideTab();
    }

    public void createProject(ActionEvent actionEvent) throws IOException {
            TreeItem<Projects> taskTreeItem = (TreeItem<Projects>) ownedProjects.getSelectionModel().getSelectedItem();
            if (taskTreeItem == null) {
                newProject(false, 0, Integer.parseInt(ownedCourses.getSelectionModel().getSelectedItem().toString().split("\\ | ")[0]), 0);
            } else {
                newProject(false, taskTreeItem.getValue().getId(), 0, 0);
            }
    }

    public void editProject(ActionEvent actionEvent) throws IOException {
        TreeItem<Projects> taskTreeItem = (TreeItem<Projects>) ownedProjects.getSelectionModel().getSelectedItem();
        newProject(true, 0, 0, taskTreeItem.getValue().getId());

    }

    public void deleteProject(ActionEvent actionEvent) {
        TreeItem<Projects> taskTreeItem = (TreeItem<Projects>) ownedProjects.getSelectionModel().getSelectedItem();
        projectHib.remove(taskTreeItem.getValue().getId());
    }

    public void newProject(boolean modif, int courseId, int projectId, int idk) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewProject.fxml"));
            Parent root = fxmlLoader.load();
            NewProject newProject = fxmlLoader.getController();
            newProject.newData(modif, projectId, courseId, userId, idk);
            Scene scene = new Scene(root);
            Stage stage = (Stage) myCoursesT.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    public void selectOwnCourse(MouseEvent mouseEvent) {
        Course selectedCourse = courseHib.getCourseById(Integer.parseInt(ownedCourses.getSelectionModel().getSelectedItem().toString().split("\\ | ")[0]));
        ownedProjects.setRoot(new TreeItem<>("Galimi projektai: "));
        ownedProjects.setShowRoot(false);
        ownedProjects.getRoot().setExpanded(true);
        selectedCourse.getCourseProjects().forEach(projects -> addTreeProject(projects, ownedProjects.getRoot()));
    }

    public void addTreeProject(Projects projects, TreeItem parent){
        TreeItem<Projects> treeItem = new TreeItem<>(projects);
        parent.getChildren().add(treeItem);
        projects.getSubProjects().forEach(temp ->addTreeProject(temp, treeItem));
    }
}
