package Dao;

import model.Book;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDao {

    public static void saveBook(Book book ) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO Book (title, authorId, price) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setInt(2, book.getAuthor().getAuthorId());
                preparedStatement.setInt(3, book.getPrice());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            e.printStackTrace();
            throw new RuntimeException("Failed to save book to the database");
        }
    }
}
