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
import za.co.rhmsolutions.scrum.business.entity.Project;
import za.co.rhmsolutions.scrum.business.entity.Task;

/**
 *
 * @author Richard O'Brien
 */
@Stateless
public class ProjectService 
{
    @PersistenceContext
    EntityManager em;
    
    public void create(String name, String company)
    {
       Project p = new Project(name, company);
       em.persist(p);
       
       System.out.println("Project ID: " + p.getId());
    }
    
    public void update(long id, String name, String company, ArrayList<String> usernames, ArrayList<ArrayList<Integer>> PreviousBurndownCharts, ArrayList<Integer> burndownPoints)
    {
        
      em.createQuery("UPDATE Project p SET p.name='" + name + "', p.company='"+ company + "', p.PreviousBurndownCharts='" + PreviousBurndownCharts
              + "', p.burndownPoints='"+ burndownPoints + "' WHERE p.id='" + id + "'").executeUpdate();
    }
    
    public Project[] getAllProjects ()
    {
        List l = em.createQuery("select c from Project c").getResultList();

        for (int i = 0; i < l.size(); i++) 
        {
            Project temp = (Project)(l.get(i));
            
            if (temp.isDeleted())
            {
                l.remove(i);
            }
        }
        
        Project[] P = new Project[l.size()];
        
        for (int i = 0; i < l.size(); i++) 
        {
            P[i] = (Project)(l.get(i));
        }
        
        for (int i = 0; i < P.length; i++) 
        {
            System.out.println("id: " + P[i].getId() + "; Project Name: " + P[i].getName() 
                    + "; Company: " + P[i].getCompany() + " etc...");
        }
        
        return P;
    }
    
    public void delete (long id)
    {
        boolean d = true;
        //em.createQuery("DELETE FROM Task t WHERE t.id='" + id+ "'").executeUpdate();
        em.createQuery("UPDATE Project p SET p.deleted='" + d + "' WHERE p.id='" + id + "'").executeUpdate();
    }
    
    public void addUserToProject()
    {
        
    }
}
