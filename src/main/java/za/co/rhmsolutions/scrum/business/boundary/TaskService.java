package za.co.rhmsolutions.scrum.business.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.rhmsolutions.scrum.business.entity.Task;

/**
 *
 * @author Richard O'Brien
 * Transactions
 * ejb on boundary
 * Entitiy manager gets injected
 * Stateless is thread safe: 
 *  - one to one relationship between thread and the Entity Manager
 * Transactions are automatically started 
 * 
 * Incase of exceptions: Transaction is automatically rolled back
 */
@Stateless 
public class TaskService 
{
    @PersistenceContext
    EntityManager em;
    public void create(String name)
    {
        em.persist(new Task(name));
    }
}
