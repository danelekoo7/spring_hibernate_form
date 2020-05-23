package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task.book.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {


    Optional<Author> findFirstByEmail(String emial);

    Optional<Author> findFirstByPesel(String pesel);

    List<Author> findByLastName(String lastName);


    @Query("SELECT a FROM Author a WHERE a.email LIKE :mail%")
    List<Author> findAuthorByEmailLike(@Param("mail") String mail);

    @Query("SELECT a FROM Author a WHERE a.pesel LIKE :pesel%")
    List<Author> findAuthorByPeselLike(@Param("pesel") String pesel);


}
