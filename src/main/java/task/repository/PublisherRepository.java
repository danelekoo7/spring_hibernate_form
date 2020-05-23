package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.book.entity.Publisher;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findFirstByNip(String nip);

    Optional<Publisher> findFirstByRegon(String regon);

}
