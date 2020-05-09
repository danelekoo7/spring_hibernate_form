package task.book.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import task.book.entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PersonDetailsDao {
    
    @PersistenceContext
    EntityManager entityManager;

    public void savePersonDetails(PersonDetails personDetails) {
        entityManager.persist(personDetails);
    }

    public void update(PersonDetails personDetails) {

        entityManager.merge(personDetails);
    }

    public PersonDetails findById(long id)
    {
        return entityManager.find(PersonDetails.class, id);
    }

    public void delete(PersonDetails personDetails) {
        entityManager.remove(entityManager.contains(personDetails) ?
                personDetails : entityManager.merge(personDetails)); }
}