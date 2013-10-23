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
