package za.co.rhmsolutions.scrum.presentation;


import Reference.Reference;
import Reference.WebSockets;
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
        //this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        //this.text = text;
    }
    
    
    public void createTask()
    {
        System.out.println("Create Task Clicked!");
        WebSockets w = Reference.w;
        for(int x=0; x< w.getTasks().size();x++)
        {
            if(w.getTasks().get(x).getUpdate())
            {
                name = w.getTasks().get(x).getID();
                text = w.getTasks().get(x).getMessage();

                System.out.println(name + "; "+ text);
                
                ts.create(name);
                w.getTasks().get(x).dbUpdate();
            }
        }
        
    }
}
