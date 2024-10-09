package springE2E.repository;

import springE2E.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);
}