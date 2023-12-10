package util;// DatabaseUtil.java

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try (InputStream input = DatabaseUtil.class.getResourceAsStream("/resources/db.properties")) {
                if (input == null) {
                    throw new RuntimeException("db.properties not found");
                }
                Properties prop = new Properties();
                prop.load(input);
                connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
                Statement st = connection.createStatement();

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

    public static void main(String[] args) {
        DatabaseUtil.getConnection();
    }
}
