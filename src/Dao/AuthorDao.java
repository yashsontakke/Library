package Dao;
import model.Author;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDao {

    public static void saveAuthor(Author author ) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO Author (name, experience , authorId) VALUES (?, ?,? )";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, author.getName());
                preparedStatement.setInt(2, author.getExp());
                preparedStatement.setInt(3, author.getAuthorId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            e.printStackTrace();
            throw new RuntimeException("Failed to save author to the database");
        }
    }
    public static void printAuthorWithMostBooks() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT authorId, name " +
                    "FROM Author " +
                    "ORDER BY (SELECT COUNT(*) FROM Book WHERE Author.authorId = Book.authorId) DESC LIMIT 1 OFFSET 0";

            try (PreparedStatement preparedStatement = conn.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Print author information directly
                    System.out.println("Author with Most Books:");
                    System.out.println("Author ID: " + resultSet.getInt("authorId") +
                            ", Name: " + resultSet.getString("name"));
                } else {
                    System.out.println("No authors found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

}
