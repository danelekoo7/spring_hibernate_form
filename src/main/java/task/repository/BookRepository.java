package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Category;
import task.book.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>,CustomizedBookRepository {

    List<Book> findByRating(Integer rating);

    List<Book> findByTitle(String title);

    List<Book> findByCategory(Category category);

    List<Book> findByCategoryId(Long id);

    List<Book> findAllByAuthors(Author author);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByRatingBetween(int start, int end);

    Optional<Book> findFirstByCategoryOrderByTitle(Category c);






    @Query("SELECT b FROM Book b where b.title = ?1")
    List<Book> customFindByTitle(String title);

    @Query("SELECT b FROM Book b where b.category = ?1")
    List<Book> customFindByCategory(Category category);


    @Query("SELECT b FROM Book b WHERE b.rating BETWEEN 3 AND 5")
    List<Book> findAllWithRatingBetween3And5();


    @Query("SELECT b FROM Book b WHERE b.publisher=:publisher")
    List<Book> customizedFindBookByPublisher(@Param("publisher") Publisher publisher);

    @Query("SELECT b FROM Book b WHERE b.category=:category")
    List<Book> findBookByCategory(@Param("category") Category category );




}