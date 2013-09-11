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

/**
 *
 * @author Richard O'Brien
 */
@Stateless
public class AppUserService 
{
    @PersistenceContext
    EntityManager em;
    
    public void create(String name, String surname, String email,String password ,long projectID)
    {
        AppUser u = new AppUser(name, surname, email, password);
        
        em.persist(u);
        
        System.out.println("User ID: " + u.getId());
        
        AppUser_Project mapper = new AppUser_Project(u.getId(), projectID);
        
        em.persist(mapper);
    }
}
