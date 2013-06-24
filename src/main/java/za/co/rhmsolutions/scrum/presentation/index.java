package za.co.rhmsolutions.scrum.presentation;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import za.co.rhmsolutions.scrum.business.boundary.TaskService;

/**
 *
 * @author Richard O'Brien
 * Model: Visible to jsf and request code
 */
@Model
public class index 
{
    @Inject
    TaskService ts;
    
    public void createTask()
    {
        System.out.println("Create Task Clicked!");
        ts.create("Task Name 2");
    }
}
