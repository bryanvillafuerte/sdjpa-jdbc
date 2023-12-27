package guru.springframework.jdbc.sdjpajdbc;

import guru.springframework.jdbc.sdjpajdbc.dao.AuthorDao;
import guru.springframework.jdbc.sdjpajdbc.dao.BookDao;
import guru.springframework.jdbc.sdjpajdbc.dao.BookDaoImpl;
import guru.springframework.jdbc.sdjpajdbc.domain.Author;
import guru.springframework.jdbc.sdjpajdbc.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.jdbc.sdjpajdbc.dao"})
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Autowired
    AuthorDao authorDao;

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setTitle("Clean Agile: Back to Basics");
        book.setIsbn("978-0135781869");
        book.setPublisher("Pearson");
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        Book deleted = bookDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }

    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setTitle("Clean Agile");
        book.setIsbn("978-0135781869");
        book.setPublisher("Pearson");

        Author author = new Author();
        author.setId(3L);

        book.setAuthor(author);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("Clean Agile: Back to Basics");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("Clean Agile: Back to Basics");
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setTitle("Clean Agile: Back to Basics");
        book.setIsbn("978-0135781869");
        book.setPublisher("Pearson");

        Author author = new Author();
        author.setId(3L);

        book.setAuthor(author);
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByName() {
        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNotNull();
    }

    @Test
    void testGetBook() {
        Book book = bookDao.getById(3L);

        assertThat(book.getId()).isNotNull();
    }
}
