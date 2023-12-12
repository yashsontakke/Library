package Dao;

import model.Book;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static void printBookInfoByTitle(String title) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Book WHERE title = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, title);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Print the details directly
                        System.out.println("Book ID: " + resultSet.getInt("id"));
                        System.out.println("Title: " + resultSet.getString("title"));
                        System.out.println("Author ID: " + resultSet.getInt("authorId"));
                        System.out.println("Price: " + resultSet.getInt("price"));
                    } else {
                        throw new SQLException("Book not found with title: " + title);
                    }
                }
            }
        }
    }
    public static void printAuthorByBookTitle(String title) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT A.* FROM Author A JOIN Book B ON A.authorId = B.authorId WHERE B.title = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, title);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Print the author information directly
                        System.out.println("Author of the book '" + title + "':");
                        System.out.println("Author ID: " + resultSet.getInt("authorId"));
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Experience: " + resultSet.getInt("experience"));
                    } else {
                        throw new SQLException("Book not found with title: " + title);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }



}
