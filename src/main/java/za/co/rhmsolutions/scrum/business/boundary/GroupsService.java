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
    
    public void create(String groupID, String username)
    {
        groups u = new groups(groupID, username);
        
        em.persist(u);
        
        System.out.println("User ID: " + u.getId());
    }
    
    public String getPrivilege(String username)
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
}
