package za.co.rhmsolutions.scrum.business.boundary;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.co.rhmsolutions.scrum.business.entity.Task;

/**
 *
 * @author Richard O'Brien
 * Transactions
 * ejb on boundary
 * Entitiy manager gets injected
 * Stateless is thread safe: 
 *  - one to one relationship between thread and the Entity Manager
 * Transactions are automatically started 
 * 
 * Incase of exceptions: Transaction is automatically rolled back
 */
@Stateless 
public class TaskService 
{
    @PersistenceContext
    EntityManager em;
    public void create(String name)
    {
        //em.persist(new Task(name));
        //System.out.println("Hello wierd code");
        

        //long id = getID();
        //System.out.println("ID Found: " + id);
        //List tmp = selectTest("152");
        //System.out.println(tmp.get(0));
    }
    
    private String status;
    private String description;
    private String responsible;
    private String points;
    private String days;
    private String colour;
    
    public void create(String name, String topPos, String leftPos, 
            String status, String description, String responsible, String points, String days, String colour)
    {
        Task t = new Task(name, topPos, leftPos, status, description, responsible, points, days, colour);
        em.persist(t);
        System.out.println("ID Found: " + t.getID());
    }
    
    //Creates new task and returns id of that specific task
    public long getID()
    {
        Task t = new Task();
        em.persist(t);
        
        long id = t.getID();
        
        System.out.println("ID Returned = " + id);
  
        getAll();
        
        return id;
    }
    
    //Updates task given its id 
    public void update (long id, String name,String topPos, String leftPos, String status, String description, 
            String responsible, String points, String days, String colour)
    { 
       em.createQuery("UPDATE Task t SET t.name='" + name + "', t.topPos='"+ topPos + "', t.leftPos='" + leftPos
               + "', t.status='"+ status + "', t.description='" + description + "', t.responsible='"+ responsible
               + "', t.points='" + points + "', t.days='"+ days + "', t.colour='" + colour +"' WHERE t.id='" + id + "'").executeUpdate();
       
       //em.createQuery("UPDATE Task t SET t.name='jjj' WHERE t.id='152'").executeUpdate();
    }
    
    //deletes task given its id
    public void delete (long id)
    {
        em.createQuery("DELETE FROM Task t WHERE t.id='" + id+ "'").executeUpdate();
    }

    public List selectTest(String name) 
    {
    return em.createQuery(
    "SELECT e FROM Task e Where e.id = '152'")
    .setMaxResults(10)
    .getResultList();
    }
    
    public void getAll()
    {
        List l = em.createQuery("select c from Task c").setMaxResults(10).getResultList();

        Task[] t = new Task[l.size()];
        
        for (int i = 0; i < l.size(); i++) 
        {
            t[i] = (Task)(l.get(i));
        }
        
        for (int i = 0; i < t.length; i++) 
        {
            System.out.println("id: " + t[i].getID() + "; Name: " + t[i].getName() 
                    + "; Colour: " + t[i].getColour() + "; Days: " + t[i].getDays() 
                    + ";Description: " + t[i].getDescription() + " etc...");
        }
        
     
    }
    
    public void listItems()
    {
        
    }
    
    
    
}
