package za.co.rhmsolutions.scrum.presentation;


import Reference.Reference;
import Reference.WebSockets;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import za.co.rhmsolutions.scrum.business.boundary.TaskService;

/**
 *
 * @author Richard O'Brien
 * Model: Visible to jsf and request code
 */
@ManagedBean(name = "index")
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
        WebSockets w = Reference.w;
        name = w.getTasks().get(0).getID();
        text = w.getTasks().get(0).getMessage();
        System.out.println(name + "; "+ text);
        System.out.println("Create Task Clicked!");
        ts.create(name);
    }
}
