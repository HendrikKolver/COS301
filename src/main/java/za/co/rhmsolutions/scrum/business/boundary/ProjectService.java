/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.boundary;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.rhmsolutions.scrum.business.entity.Project;
import za.co.rhmsolutions.scrum.business.entity.Sprint;
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
    
    @Inject
    SprintService ps;
    
    public long create(String name)
    {
        Project p = new Project(name);
        em.persist(p);
        
        Sprint s = new Sprint(p.getId());
        em.persist(s);
        
        System.out.println("Project persisted!");
        
       long id = p.getId();
       System.out.println("Project ID: " + p.getId());
       
       return id;
    }
    
    public void setRoomID(long id, String roomID)
    {
        List l = em.createQuery("select p from Project p WHERE p.id='" + id + "'").getResultList();
        
        if (l.size() == 1)
        {
            //i.e. unique result
            Project p = (Project) l.get(0);
            p.setRoom_ID(roomID);
            em.merge(p);
            System.out.println("Room ID added to project!!");
        }
        else
        {
            System.out.println("WARNING! Failed to add Room ID!");
        }
    }
    
    public void update(long id, String name, ArrayList<String> usernames, ArrayList<ArrayList<Integer>> PreviousBurndownCharts, ArrayList<Integer> burndownPoints)
    {
       //List l = em.createQuery("select p from Project p WHERE p.id='" + id + "'").setMaxResults(10).getResultList();
        List l = em.createQuery("select s from Sprint s WHERE s.projectID='" + id + "'").getResultList();
        int size = l.size();
        
        System.out.println("PreviousBurndownCharts size: " + PreviousBurndownCharts.size());
        System.out.println("l size: " + l.size());
        
        if (PreviousBurndownCharts.size() == l.size())
        {
            //i.e. new sprint!
            for (int i = 0; i < l.size(); i++) 
            {
                Sprint temp = (Sprint) l.get(i);
                temp.setActive(false);
                em.merge(temp);
            }

            Sprint temp = (Sprint) l.get(size - 1);
            temp.setBurndownChart(PreviousBurndownCharts.get(PreviousBurndownCharts.size() - 1));
            em.merge(temp);
            
            Sprint s = new Sprint(id);
            em.persist(s);
        }
        else
        {
            Sprint temp = (Sprint) l.get(size-1);
            
            if (temp.isActive())
            {
                temp.setBurndownChart(burndownPoints);
                em.merge(temp);
            }
            else
            {
                System.out.println("WARNING! Last sprint set to false!!!");
            }   
        }
        
        List j = em.createQuery("select p from Project p WHERE p.id='" + id + "'").getResultList();
        
        System.out.println("Project Count returned: " + j.size());
        
        if (j.size() == 1)
        {
            //i.e. unique result
            Project p = (Project) j.get(0);
            p.setName(name);
            p.setUsernames(usernames);
            em.merge(p);
            System.out.println("Project Updated!");
        }
        else
        {
            System.out.println("WARNING! Project Update Failed!");
        }

    }
    
    public Project[] getAllProjects ()
    {  
        Project [] P;
                
        try 
        {
           List l = em.createQuery("select p from Project p").getResultList();
           
           
            for (int i = 0; i < l.size(); i++) 
            {
                Project temp = (Project)(l.get(i));
            
                if (temp.isDeleted())
                {
                    l.remove(i);
                }
            }
            
           P = new Project[l.size()]; 
           
           for (int i = 0; i < l.size(); i++) 
           {
                P[i] = (Project)(l.get(i));
           }
           
            System.out.println("Number of Projects: " + P.length);
           return P;
        }
        catch (Exception e)
        {
            System.out.println("Broke Here!!!!!!!!!!!!!");
        }

        P = new Project[0];
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
    
    public ArrayList<ArrayList<Integer>> getBurndownCharts(long id)
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Sprint current = new Sprint();
        current.setActive(false);
        try
        {
            List l = em.createQuery("select s from Sprint s WHERE s.projectID='" + id + "'").getResultList();
            System.out.println("Number of burndown charts found: " + l.size());
            for (int i = 0; i < l.size(); i++) 
            {
                Sprint temp = (Sprint) l.get(i);
                if (temp.isActive())
                {
                    current = temp;
                }
                else
                {
                    result.add(temp.getBurndownChart());
                }
            }
            
            if (current.isActive())
            {
                result.add(current.getBurndownChart());
            }
            else
            {
                System.out.println("WARNING: No current burndown chart found!");
            }
            
        }
        catch (Exception e)
        {
            System.out.println("Exception Found: ProjectService: getBurndownCharts");
        }
        
        return result;
    }
}
