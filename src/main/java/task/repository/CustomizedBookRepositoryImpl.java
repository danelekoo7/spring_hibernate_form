package task.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
public class CustomizedBookRepositoryImpl implements CustomizedBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void resetRating(int rating) {
        Query query = entityManager.createQuery("UPDATE Book b SET b.rating =:rating");
        query.setParameter("rating", rating).executeUpdate();
    }
}
