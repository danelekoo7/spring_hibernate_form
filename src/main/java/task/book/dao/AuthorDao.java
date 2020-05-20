package task.book.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import task.book.entity.Author;
import task.book.entity.Book;
import task.book.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    EntityManager entityManager;

//    day2


    public List<Author> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM Author a");
        List<Author> authors = query.getResultList();
        return authors;
    }


//    public List<Author> findByBook(Book book) {
//        Query query = entityManager.createQuery("SELECT a FROM Author a JOIN ");
//        List<Author> authors = query.getResultList();
//        return authors;
//    }


//    day1

    public void saveAuthor(Author author) {
        entityManager.persist(author);
    }

    public void update(Author author) {
        entityManager.merge(author);
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void delete(Author author) {
        entityManager.remove(entityManager.contains(author) ?
                author : entityManager.merge(author));
    }
}
