/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.boundary;

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
    
    public void create(String GROUPID, String USERNAME)
    {
        groups u = new groups(GROUPID, USERNAME);
        
        em.persist(u);
        
        System.out.println("User ID: " + u.getId());
    }
}
