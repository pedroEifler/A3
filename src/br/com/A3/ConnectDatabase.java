package br.com.A3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDatabase {
    public Statement connect() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/a3", "root", "root");
            return connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
