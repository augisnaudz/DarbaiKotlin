module com.example.augustino {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    requires spring.web;
    requires spring.context;
    requires spring.core;
    requires com.google.gson;

    opens com.example.augustino.DS to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.example.augustino.DS;
    exports com.example.augustino.fxcontrol;
    opens com.example.augustino.fxcontrol to javafx.fxml, java.persistence;
}