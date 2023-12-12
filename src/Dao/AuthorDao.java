package Dao;
import model.Author;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDao {

    public static void saveAuthor(Author author ) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO Author (name, experience , authorId) VALUES (?, ?)";
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

}
