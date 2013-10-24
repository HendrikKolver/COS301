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

/**
 * Creates and persists a new task to the database
 * The create method of the TaskService class.
 * 
 * @param  name  The string name of the task
 * @param  topPos The string x coordinates of the task on the scrumboard
 * @param  leftpos The string y coordinates of the task on the scrumboard
 * @param  status The string status of the task (Not started, In Progress, Complete)
 * @param  description The string description of the task for further information
 * @param  responsible The string string name of the person/people/team responsible for the task
 * @param  points The string number of points assigned to the task
 * @param  days The string number of days assigned to the task
 * @param  colour The string colour of the sticky note used to represent the task
 * @param  comments Any string comments related to the task
 * @param  subTasks The string Sub Tasks of the task
 * @param  projectId The string ID of the project the task is assigned to
 * @param  sprintBacklog A boolean flag to indicate if the task is currently in a sprint
 */
    public void create(String name, String topPos, String leftPos, 
            String status, String description, String responsible, String points, String days, String colour, String comments, String subTasks, String projectID,boolean sprintBacklog)
    {
        try
        {
            Task t = new Task(name, topPos, leftPos, status, description, responsible, points, days, colour, comments, subTasks, projectID, sprintBacklog);
            em.persist(t);
            System.out.println("ID Found: " + t.getID());    
        }
        catch (Exception e)
        {
            System.out.println("Warning: TaskService, create");
        }  
    }

/**
 * Returns the task ID of a newly created task. 
 * Creates new task and returns id of that specific task 
 *
 * @return      the ID      
 */
    public long getID()
    {
        try
        {
            Task t = new Task();
            em.persist(t);

            long id = t.getID();

            System.out.println("ID Returned = " + id);

            getAll();

            return id; 
        }
        catch (Exception e)
        {
            System.out.println("Warning: TaskService, getID");
        }
        
        return -1;
    }
    
/**
 * Updates a task in the database using id
 * The update method of the TaskService class.
 * 
 * @param  name  The string name of the task
 * @param  topPos The string x coordinates of the task on the scrumboard
 * @param  leftpos The string y coordinates of the task on the scrumboard
 * @param  status The string status of the task (Not started, In Progress, Complete)
 * @param  description The string description of the task for further information
 * @param  responsible The string string name of the person/people/team responsible for the task
 * @param  points The string number of points assigned to the task
 * @param  days The string number of days assigned to the task
 * @param  colour The string colour of the sticky note used to represent the task
 * @param  comments Any string comments related to the task
 * @param  subTasks The string Sub Tasks of the task
 * @param  projectId The string ID of the project the task is assigned to
 * @param  sprintBacklog A boolean flag to indicate if the task is currently in a sprint
 */
    public void update (long id, String name,String topPos, String leftPos, String status, String description, 
            String responsible, String points, String days, String colour, String comments, String subTasks, String projectID,boolean sprintBacklog)
    { 
        try
        {
            em.createQuery("UPDATE Task t SET t.name='" + name + "', t.topPos='"+ topPos + "', t.leftPos='" + leftPos
               + "', t.status='"+ status + "', t.description='" + description + "', t.responsible='"+ responsible
               + "', t.points='" + points + "', t.days='"+ days + "', t.colour='" + colour +"', t.comments='" + comments +"', t.subTasks='" + subTasks +"', t.projectID='" + projectID + "', t.sprintBacklog='" + sprintBacklog + "' WHERE t.id='" + id + "'").executeUpdate(); 
        }
        catch (Exception e)
        {
            System.out.println("Warning: TaskService, update");
        }
    }
    
/**
 * Delete a task in the database using id
 * The delete method of the TaskService class. Sets a delete flag.
 * 
 * @param  id  The id of the task
 */
    public void delete (long id)
    {
        try
        {
            boolean d = true;
            em.createQuery("UPDATE Task t SET t.deleted='" + d + "' WHERE t.id='" + id + "'").executeUpdate();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: TaskService, delete");
        }
    }

/**
 * Returns all non deleted tasks. 
 * The get all tasks method of the TaskService class 
 *
 * @return      the Task[] of all unremoved tasks      
 */
    public Task[] getAll()
    {
        try
        {
            List l = em.createQuery("select c from Task c").getResultList();

            for (int i = 0; i < l.size(); i++) 
            {
                Task temp = (Task)(l.get(i));

                if (temp.isDeleted())
                {
                    l.remove(i);
                }
            }

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

            return t;    
        }
        catch (Exception e)
        {
            System.out.println("Warning: TaskService, getAll");
        }
        
        Task t = new Task();
        Task[] temp = new Task[1];
        temp[0] = t;
        
        return temp;
    }
 
}
