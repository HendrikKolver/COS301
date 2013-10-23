/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.boundary;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.rhmsolutions.scrum.business.entity.Sprint;

/**
 *
 * @author Richard
 */

@Stateless
public class SprintService 
{
    @PersistenceContext
    EntityManager em;
    
    public void create(Long projectID, ArrayList<Integer> bChart)
    {
        try
        {
            boolean t = true;
            List l = em.createQuery("select c from Sprint c WHERE c.projectID='" + projectID + "' AND c.active='" + t + "'").getResultList();

            Sprint[] sp = new Sprint[l.size()];

            for (int i = 0; i < l.size(); i++) 
            {
                sp[i] = (Sprint)(l.get(i));
            }

            for (int i = 0; i < sp.length; i++) {
                sp[i].setActive(false);
                em.persist(sp[i]);
                System.out.println("Active Sprint Changed!");
            }

            Sprint s = new Sprint(projectID, bChart);
            em.persist(s);
        }
        catch (Exception e)
        {
            System.out.println("Warning: SprintService, create");
        }    
    }
    
    public void create(Long projectID)
    {
        try
        {
            boolean t = true;
            List l = em.createQuery("select c from Sprint c WHERE c.projectID='" + projectID + "' AND c.active='" + t + "'").getResultList();

            Sprint[] sp = new Sprint[l.size()];

            for (int i = 0; i < l.size(); i++) 
            {
                sp[i] = (Sprint)(l.get(i));
            }

            for (int i = 0; i < sp.length; i++) {
                sp[i].setActive(false);
                em.persist(sp[i]);
                System.out.println("Active Sprint Changed!");
            }

            Sprint s = new Sprint(projectID);
            em.persist(s);     
        }
        catch (Exception e)
        {
            System.out.println("Warning: SprintService, create");
        }
    }
    
    public Sprint[] getAll(Long projectId)
    {
        try
        {
            List l = em.createQuery("select c from Sprint c WHERE c.projectID='" + projectId + "'").getResultList();

            Sprint[] s = new Sprint[l.size()];

            for (int i = 0; i < l.size(); i++) 
            {
                s[i] = (Sprint)(l.get(i));
            }

            return s;    
        }
        catch (Exception e)
        {
            System.out.println("Warning: SprintService, getAll");
        }
        
        Sprint temp = new Sprint();
        Sprint[] t = new Sprint[1];
        t[0] = temp;
        
        return t;
    }
    
    public void updateCurrentSprint(Long projectID, ArrayList<Integer> bChart)
    {
        try
        {
            boolean active = true;
            em.createQuery("UPDATE Sprint t SET t.burndownChart='" + bChart + "' WHERE t.projectID='" + projectID + "' AND t.active='" + active+ "'").executeUpdate();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: SprintService, updateCurrentSprint");
        } 
    }
}
