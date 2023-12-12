package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {

    private static Connection connection = null;



    public static Connection getConnection() throws SQLException {
        System.out.println("getting called");
        if (connection != null && !connection.isClosed()) {
            return connection;
        } else {
            try (InputStream input = DatabaseUtil.class.getResourceAsStream("/resources/db.properties")) {
                if (input == null) {
                    throw new RuntimeException("db.properties not found");
                }

                Properties prop = new Properties();
                prop.load(input);

                connection = DriverManager.getConnection(
                        prop.getProperty("db.url"),
                        prop.getProperty("db.user"),
                        prop.getProperty("db.password"));

            } catch (IOException | SQLException e) {
                // Log the exception or throw a custom exception
                e.printStackTrace();
                throw new RuntimeException("Failed to connect to the database");
            }
            return connection;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Log the exception or throw a custom exception
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }

}
