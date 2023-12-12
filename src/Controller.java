import Dao.AuthorDao;
import Dao.BookDao;
import model.Author;
import model.Book;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Controller {

   public void seedData(){
       // Create authors
       Author author1 = new Author("Mike Mentzer", 30, 1);
       Author author2 = new Author("Stephen King", 40, 2);
       Author author3 = new Author("Jane Austen", 35, 3);

       // Save authors to the database
       AuthorDao.saveAuthor(author1);
       AuthorDao.saveAuthor(author2);
       AuthorDao.saveAuthor(author3);

       // Authors writing multiple books
       Book book1ByAuthor1 = new Book("High Intensity Training", author1, 100);
       BookDao.saveBook(book1ByAuthor1);

       Book book2ByAuthor1 = new Book("Mindful Bodybuilding", author1, 120);
       BookDao.saveBook(book2ByAuthor1);

       Book book1ByAuthor2 = new Book("The Shining", author2, 150);
       BookDao.saveBook(book1ByAuthor2);

       Book book2ByAuthor2 = new Book("It", author2, 180);
       BookDao.saveBook(book2ByAuthor2);

       Book book1ByAuthor3 = new Book("Pride and Prejudice", author3, 80);
       BookDao.saveBook(book1ByAuthor3);

       Book book2ByAuthor3 = new Book("Sense and Sensibility", author3, 90);
       BookDao.saveBook(book2ByAuthor3);
   }


    public void createBookTable() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS Book (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "title VARCHAR(255) NOT NULL," +
                    "authorId INT," +  // Assuming this is a foreign key referencing Author table
                    "price INT NOT NULL," +
                    "FOREIGN KEY (authorId) REFERENCES Author(id)" +
                    ")";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.executeUpdate();
            }
            System.out.println("Book table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createAuthorTable() {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS Author (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "experience INT NOT NULL" +
                    "authorId INT NOT NULL"+
                    ")";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.executeUpdate();
            }
            System.out.println("Author table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
