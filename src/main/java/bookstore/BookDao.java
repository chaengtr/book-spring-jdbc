package bookstore;

import java.util.List;

public interface BookDao {
    void save(Book book);
    void update( int id, Book book);
    void deleteById( int id);
    Book findById( int id);
    List<Book> findAll();
}
