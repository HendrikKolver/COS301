/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.rhmsolutions.scrum.business.entity.groups;

/**
 *
 * @author Richard O'Brien
 */
@Stateless
public class GroupsService 
{
    @PersistenceContext
    EntityManager em;
    
/**
 * Creates and persists a new user role to the database
 * The create method of the TaskService class.
 * 
 * @param  groupID  The string groupID of the role/privileges of the user (admin,guest)
 * @param  username The string username of an already defined AppUser
 */
    public void create(String groupID, String username)
    {
        try
        {
            groups u = new groups(groupID, username);

            em.persist(u);

            System.out.println("User ID: " + u.getId());
        }
        catch(Exception e)
        {
            System.out.println("Warning: GroupsService, create");
        }
    }
 
/**
 * Returns the privilege level of a specific AppUser. 
 * 
 * @param  username the username of the AppUser
 * @return the ID      
 */    
    public String getPrivilege(String username)
    {
        try
        {
            List l = em.createQuery("select c.GroupId from groups c WHERE c.Username='" + username + "'").setMaxResults(1).getResultList();

            String privilegeLevel;

            if (l.size() == 1)
            {
                privilegeLevel = (String) l.get(0);
            }
            else
            {
                privilegeLevel = "NOT FOUND";
                System.out.println("WARNING: USER HAS NO PRIVILEGE LEVEL!");
            }

            return privilegeLevel;    
        }
        catch(Exception e)
        {
            System.out.println("Warning: GroupsService, getPrivilege");
        }
        
        return "guest";
    }
}
