/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.rhmsolutions.scrum.business.entity.AppUser;
import za.co.rhmsolutions.scrum.business.entity.AppUser_Project;
import za.co.rhmsolutions.scrum.business.entity.Project;
import za.co.rhmsolutions.scrum.business.entity.Task;
import za.co.rhmsolutions.scrum.business.entity.groups;

/**
 *
 * @author Richard O'Brien
 */
@Stateless
public class AppUserService 
{
    @PersistenceContext
    EntityManager em;
    
    public void createUser(String name, String surname, String username,String password)
    {
        AppUser u = new AppUser(name, surname, username, password);
        
        List l = em.createQuery("select c from AppUser c WHERE c.Username='" + username + "'").setMaxResults(10).getResultList();
        
        if (l.size() > 0)
        {
            System.out.println("USERNAME ALREADY USED");
            return;
        }
        em.persist(u);
        
        System.out.println("User ID: " + u.getId());
        
        groups g = new groups("admin", username);
        em.persist(g);
    }
    
    public void createAdmin(String name, String surname, String username,String password)
    {
        AppUser u = new AppUser(name, surname, username, password);
        
        List l = em.createQuery("select c from AppUser c WHERE c.Username='" + username + "'").setMaxResults(10).getResultList();
        
        if (l.size() >= 0)
        {
            System.out.println("USERNAME ALREADY USED");
            return;
        }
        em.persist(u);
        
        System.out.println("User ID: " + u.getId());
        
        groups g = new groups("admin", username);
    }
    
    public AppUser getByUsername(String username)
    {
       List l = em.createQuery("select a from AppUser a WHERE a.Username='" + username + "'").setMaxResults(1).getResultList();
       
       AppUser u = (AppUser) l.get(0);
       
       return u;
    }
    
    public void delete (long id)
    {
        boolean d = true;
        //em.createQuery("DELETE FROM Task t WHERE t.id='" + id+ "'").executeUpdate();
        em.createQuery("UPDATE AppUser t SET t.deleted='" + d + "' WHERE t.id='" + id + "'").executeUpdate();
    }
    
    public AppUser[] getAllAppUsers()
    {
        List l = em.createQuery("select a from AppUser a").getResultList();

        for (int i = 0; i < l.size(); i++) 
        {
            AppUser temp = (AppUser)(l.get(i));
            
            if (temp.isDeleted())
            {
                l.remove(i);
            }
        }
        
        AppUser[] a = new AppUser[l.size()];
        
        for (int i = 0; i < l.size(); i++) 
        {
            a[i] = (AppUser)(l.get(i));
        }
        
        for (int i = 0; i < a.length; i++) 
        {
            System.out.println("id: " + a[i].getId() + "; Username: " + a[i].getUsername() 
                    + " etc...");
        }
        
        return a;
    }
    
    public void updateUser(String name, String surname, String username,String password)
    {
        em.createQuery("UPDATE AppUser a SET a.name='" + name + "', a.surname='"+ surname + "', a.Username='" + username
              + "', a.Password='"+ password + "' WHERE a.Username='" + username + "'").executeUpdate();
    }

}
