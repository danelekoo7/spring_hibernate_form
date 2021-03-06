package task.book.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;

   private final PublisherDao publisherDao;
   private  final AuthorDao authorDao;

    public BookDao(PublisherDao publisherDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

//    day2

    public List<Book> findAll(){
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        List<Book> books = query.getResultList();

        return books;
    }


    public List<Book> findAllProposition(){
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.proposition = true ");
        List<Book> books = query.getResultList();

        return books;
    }

    public List<Book> findAllWithRatingGreaterThen(Integer rating){
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating > :rating");
        query.setParameter("rating", rating);

        List<Book> books = query.getResultList();

        return books;
    }

    public List<Book> findAllWithPublisher(){
        Query query = entityManager.createQuery("SELECT b FROM Book b JOIN b.publisher");

        List<Book> books = query.getResultList();

        return books;
    }



    public List<Book> findAllWithPublisherEquals(Long id){
        Publisher publisher = publisherDao.findById(id);
        Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher=:publisher");
        query.setParameter("publisher", publisher);
        List<Book> books = query.getResultList();
        return books;
    }






    public List<Book> findAllWithAuthors(){
        Query query = entityManager.createQuery("SELECT b FROM Book b JOIN b.authors");

        List<Book> books = query.getResultList();

        return books;
    }


    public List<Book> findAllWithAuthorsEquals(Long id){
        Query query = entityManager.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a.id=:id");
        query.setParameter("id", id);
        List<Book> books = query.getResultList();
        return books;
    }



//    day1
    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book)); }
}