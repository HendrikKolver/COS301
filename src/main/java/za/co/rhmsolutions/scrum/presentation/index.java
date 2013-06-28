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
    String topPos = "topPos";
    String leftPos = "leftPos";
    String status = "status";
    String description = "description";
    String responsible = "responsible";
    String points = "points";
    String days = "days";
    String colour = "colour";
    String updateID;
    String deleteID;

    public String getDeleteID() {
        return deleteID;
    }

    public void setDeleteID(String deleteID) {
        this.deleteID = deleteID;
    }

    public String getUpdateID() {
        return updateID;
    }

    public void setUpdateID(String updateID) {
        this.updateID = updateID;
    }

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
    
    //Creates new task for all new changes using values in w. Not for production purposes
    public void createTask()
    {
        System.out.println("Create Task Clicked!");
        WebSockets w = Reference.w;
        for(int x=0; x< w.getTasks().size();x++)
        {
            if(w.getTasks().get(x).getUpdate())
            {
                name = w.getTasks().get(x).getName();
                topPos = w.getTasks().get(x).getTopPos();
                leftPos = w.getTasks().get(x).getLeftPos();
                status = w.getTasks().get(x).getStatus();
                description = w.getTasks().get(x).getDescription();
                responsible = w.getTasks().get(x).getResponsible();
                points = w.getTasks().get(x).getPoints();
                days = w.getTasks().get(x).getDays();
                colour = w.getTasks().get(x).getColour();

                ts.create(name, topPos, leftPos, status, description, responsible, points, days, colour);
                
                w.getTasks().get(x).dbUpdate();
            }
        }
    }
    
    //Update task using value of updateID
    public void update()
    {
        WebSockets w = Reference.w;
        for(int x=0; x< w.getTasks().size();x++)
        {
            if(w.getTasks().get(x).getUpdate() && w.getTasks().get(x).getID().equals(updateID))
            {
                name = w.getTasks().get(x).getName();
                topPos = w.getTasks().get(x).getTopPos();
                leftPos = w.getTasks().get(x).getLeftPos();
                status = w.getTasks().get(x).getStatus();
                description = w.getTasks().get(x).getDescription();
                responsible = w.getTasks().get(x).getResponsible();
                points = w.getTasks().get(x).getPoints();
                days = w.getTasks().get(x).getDays();
                colour = w.getTasks().get(x).getColour();

                long id;
                id = Long.valueOf(updateID).longValue();
                
                ts.update(id, name, topPos, leftPos, status, description, responsible, points, days, colour);
                System.out.println("Updated task " + id);
                w.getTasks().get(x).dbUpdate();
                
                break;
            }
        }
    }
    
    //Creates a template task using default values and returns id as String
    public String getID()
    {
        long id;
        id = ts.getID();
        
        String s = String.valueOf(id);
        
        System.out.println("String id: " + s);
        
        return s;
    }
    
    public void delete()
    {
        WebSockets w = Reference.w;
       for(int x=0; x< w.getTasks().size();x++)
       {
            if(w.getTasks().get(x).getID().equals(deleteID))
            {
                long id;
                id = Long.valueOf(deleteID).longValue();
                
                ts.delete(id);
                System.out.println("Delete task " + id);
                w.getTasks().get(x).dbUpdate();
                
               break;
            }
        }
    }
}
