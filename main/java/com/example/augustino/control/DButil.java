package com.example.augustino.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DButil {
    public static Connection connectToDb() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost/kursusys3";
            String USER = "root";
            String PASS = "";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException t) {
            t.printStackTrace();
        }
        return conn;
    }

    public static void disconnectFromDb(Connection connection, Statement statement) {

        try {
            if (connection != null && statement != null) {
                connection.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
