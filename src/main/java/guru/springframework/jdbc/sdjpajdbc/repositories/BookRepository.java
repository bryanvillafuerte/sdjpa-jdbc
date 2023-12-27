package guru.springframework.jdbc.sdjpajdbc.repositories;

import guru.springframework.jdbc.sdjpajdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
