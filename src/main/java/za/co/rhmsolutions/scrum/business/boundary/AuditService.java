/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.rhmsolutions.scrum.business.entity.Audit;

/**
 *
 * @author Richard O'Brien
 */
@Stateless
public class AuditService 
{
    @PersistenceContext
    EntityManager em;
    
/**
 * Creates and persists a new Audit log to the database
 * The create method of the AuditService class.
 * 
 * @param  log  The string log of the Audit containing users action details
 */    
    public void create(String log)
    {
        try
        {
            Audit a = new Audit(log);
            em.persist(a);    
        }
        catch(Exception e)
        {
            System.out.println("Warning: AuditService, create");
        } 
    }

/**
 * Returns a count of the number of logs in the Database
 * The countLogs method of the AuditService class.
 * 
 * @return      the integer count of log occurrences 
 */     
    public int countLogs()
    {
        try
        {
            List l = em.createQuery("select a from Audit a").getResultList();

            int count = l.size();

            return count;    
        }
        catch(Exception e)
        {
            System.out.println("Warning: AuditService, countLogs");
        }
        
        return -1;
    }
    
}
