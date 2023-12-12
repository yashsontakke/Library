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

    public static void printBookInfoByTitle(String title) {
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void printAuthorByBookTitle(String title) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void printBooksByPriceRange(int minPrice, int maxPrice)  {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Book WHERE price BETWEEN ? AND ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, minPrice);
                preparedStatement.setInt(2, maxPrice);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Books with price between $" + minPrice + " and $" + maxPrice + ":");
                        do {
                            // Print book information directly
                            System.out.println("Title: " + resultSet.getString("title") +
                                    ", Author ID: " + resultSet.getInt("authorId") +
                                    ", Price: $" + resultSet.getInt("price"));
                        } while (resultSet.next());
                    } else {
                        System.out.println("No books found within the specified price range.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void printAllBooks() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Book";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("All Books:");
                    do {
                        // Print book information directly
                        System.out.println("Title: " + resultSet.getString("title") +
                                ", Author ID: " + resultSet.getInt("authorId") +
                                ", Price: $" + resultSet.getInt("price"));
                    } while (resultSet.next());
                } else {
                    System.out.println("No books found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void printAllBooksSortedByTitle() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Book ORDER BY title";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("All Books Sorted by Title:");
                    do {
                        // Print book information directly
                        System.out.println("Title: " + resultSet.getString("title") +
                                ", Author ID: " + resultSet.getInt("authorId") +
                                ", Price: $" + resultSet.getInt("price"));
                    } while (resultSet.next());
                } else {
                    System.out.println("No books found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void printBooksStartingWithS() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Book WHERE title LIKE 's%'";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Books Starting with 's':");
                    do {
                        // Print book information directly
                        System.out.println("Title: " + resultSet.getString("title") +
                                ", Author ID: " + resultSet.getInt("authorId") +
                                ", Price: $" + resultSet.getInt("price"));
                    } while (resultSet.next());
                } else {
                    System.out.println("No books found starting with 's'.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void printBookWithHighestPrice() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Book ORDER BY price DESC LIMIT 1";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Print book information directly
                    System.out.println("Book with Highest Price:");
                    System.out.println("Title: " + resultSet.getString("title") +
                            ", Author ID: " + resultSet.getInt("authorId") +
                            ", Price: $" + resultSet.getInt("price"));
                } else {
                    System.out.println("No books found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void printBookWithLongestTitle() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Book ORDER BY LENGTH(title) DESC LIMIT 1";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Print book information directly
                    System.out.println("Book with Longest Title:");
                    System.out.println("Title: " + resultSet.getString("title") +
                            ", Author ID: " + resultSet.getInt("authorId") +
                            ", Price: $" + resultSet.getInt("price"));
                } else {
                    System.out.println("No books found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void printBooksByAuthorName(String authorName) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM Book WHERE authorId IN (SELECT id FROM Author WHERE name = ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, authorName);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Books by Author '" + authorName + "':");
                        do {
                            // Print book information directly
                            System.out.println("Title: " + resultSet.getString("title") +
                                    ", Author ID: " + resultSet.getInt("authorId") +
                                    ", Price: $" + resultSet.getInt("price"));
                        } while (resultSet.next());
                    } else {
                        System.out.println("No books found by Author '" + authorName + "'.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }



}
