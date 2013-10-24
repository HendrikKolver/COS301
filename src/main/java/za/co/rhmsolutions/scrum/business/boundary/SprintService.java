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
 * @author Richard O'Brien
 */

@Stateless
public class SprintService 
{
    @PersistenceContext
    EntityManager em;
    
/**
 * Creates and persists a new Sprint associated with a Project to the database
 * The create method of the SprintService class.
 * 
 * @param  projectID  The id of the associated Project
 * @param  bChart The ArrayList of integer values representing the burndownChart
 */
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

/**
 * Creates and persists a new Sprint associated with a Project, with a blank BurndownChart to the database
 * The create method of the SprintService class.
 * 
 * @param  projectID  The id of the associated Project
 * @param  bChart The ArrayList of integer values representing the burndownChart
 */    
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

/**
 * Returns all Sprints associated to a project. 
 * The get all sprints method of the TaskService class 
 *
 * @param  projectId the Long projectId of a Project
 * @return      the Sprint[] of all associated Sprints      
 */    
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

/**
 * Updates the current sprint of a project in the database using the active flag
 * The update method of the SprintService class.
 * 
 * @param  projectID  The id of the associated Project
 * @param  bChart The ArrayList of integer values representing the burndownChart
 */    
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
