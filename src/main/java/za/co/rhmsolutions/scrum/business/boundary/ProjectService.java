/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.rhmsolutions.scrum.business.entity.Project;

/**
 *
 * @author Richard O'Brien
 */
@Stateless
public class ProjectService 
{
    @PersistenceContext
    EntityManager em;
    
    public void create(String name, String company, String description)
    {
       Project p = new Project(name, company, description);
       em.persist(p);
       
       System.out.println("Project ID: " + p.getId());
    }
}
