package task.book.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import task.book.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PersonDao {
    
    @PersistenceContext
    EntityManager entityManager;


    public void savePerson(Person person) {
        entityManager.persist(person);
    }

    public void update(Person person) {

        entityManager.merge(person);
    }

    public Person findById(long id)
    {
        return entityManager.find(Person.class, id);
    }

    public void delete(Person person) {
        entityManager.remove(entityManager.contains(person) ?
                person : entityManager.merge(person)); }
    
}
