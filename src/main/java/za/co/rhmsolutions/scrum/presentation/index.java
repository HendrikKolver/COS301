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
    String name;
    String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    public void createTask()
    {
        System.out.println(name + "; "+ text);
        System.out.println("Create Task Clicked!");
        ts.create(name);
    }
}
