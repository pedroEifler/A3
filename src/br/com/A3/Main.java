package br.com.A3;

import br.com.A3.models.Book;
import br.com.A3.models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        List<Category> categories = selectAllCategories();
        List<Book> books = selectAllBooks();

        books.forEach(book -> System.out.println(book.getCategory().getName()));

    }

    private static void insert(String name) throws SQLException {
        Statement statement = new ConnectDatabase().connect();
        statement.execute("INSERT INTO book (name) VALUES ("+name+")");
    }

    private static List<Book> selectAllBooks() throws SQLException {
        Statement statement = new ConnectDatabase().connect();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
        List<Book> list = new ArrayList<>();

        while (resultSet.next()) {
            Book book = new Book();
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            int categoryId = resultSet.getInt("category");
            book.setCategory(selectAllCategory(categoryId));
            list.add(book);
        }

        return list;

    }

    private static List<Category> selectAllCategories() throws SQLException {
        Statement statement = new ConnectDatabase().connect();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM category");
        List<Category> list = new ArrayList<>();

        while (resultSet.next()) {
            Category category = new Category();
            category.setName(resultSet.getString("name"));
            list.add(category);
        }

        return list;
    }

    private static Category selectAllCategory(int id) throws SQLException {
        Statement statement = new ConnectDatabase().connect();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE id = " + id);
        Category category = new Category();

        while (resultSet.next()) {
            category.setName(resultSet.getString("name"));
        }

        return category;
    }
}
