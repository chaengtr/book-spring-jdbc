package bookstore;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImp implements BookDao {
    private JdbcTemplate jdbcTemplate;

    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        String query = "INSERT INTO book (id, name, price) VALUES (?, ?, ?);";
        Object[] data = new Object[]
                { book.getId(), book.getName(), book.getPrice() };
        jdbcTemplate.update(query, data);
    }

    public void update(int id, Book book) {
        String query = "UPDATE book set name = ?, price = ? WHERE id = " + id;
        jdbcTemplate.update(query, book.getName(), book.getPrice());
    }

    public void deleteById(int id) {
        String query = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public Book findById(int id) {
        String query = "SELECT * FROM book WHERE id = " + id;
        Book book = jdbcTemplate.queryForObject(query, new BookRowMapper());
        return book;
    }

    public List<Book> findAll() {
        String query = "SELECT * FROM book" ;
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;
    }

    class BookRowMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book(rs.getInt( "id" ),
                rs.getString( "name" ),
                rs.getDouble( "price" ));
            return book;
        }
    }
}
