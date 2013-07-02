package za.co.rhmsolutions.scrum.presentation;


import Reference.Reference;
import Reference.Tasks;
import Reference.WebSockets;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import za.co.rhmsolutions.scrum.business.boundary.TaskService;
import za.co.rhmsolutions.scrum.business.entity.Task;

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
    String ID;
    String flag = "false";
    String comments;
    String subTasks;

    public String getFlag() {
        System.out.println("andBack");
        return flag;
    }

    public void setFlag(String flag) {
        System.out.println("here");
        this.flag = flag;
    }

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
    
    public void setID(String id)
    {
        ID = id;
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
                comments = w.getTasks().get(x).getComments();
                subTasks = w.getTasks().get(x).getSubTasks();

                ts.create(name, topPos, leftPos, status, description, responsible, points, days, colour, comments, subTasks);
                
                w.getTasks().get(x).dbUpdate();
            }
        }
    }
    
    //Update task using value of updateID
    public void update()
    {
        WebSockets w = Reference.w;
        System.out.println(w.getTasks().size());
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
                comments = w.getTasks().get(x).getComments();
                subTasks = w.getTasks().get(x).getSubTasks();
                
                
                long id;
                id = Long.valueOf(updateID).longValue();
                
                ts.update(id, name, topPos, leftPos, status, description, responsible, points, days, colour, comments, subTasks);
                System.out.println("Updated task " + id);
                w.getTasks().get(x).dbUpdate();
                
                break;
            }
        }
    }
    
    //Creates a template task using default values and returns id as String
    public void updateID()
    {  
            long id;
            id = ts.getID();
            ID = String.valueOf(id);
            System.out.println("String id: " + ID);
            WebSockets w = Reference.w;
            w.addTask(ID);
            w.sendTasks();
    }
    
    public void delete()
    {
        WebSockets w = Reference.w;
       
        long id;
        id = Long.valueOf(deleteID).longValue();

        ts.delete(id);
        System.out.println("Delete task " + id);      
    }
    
    public void getAllTasks()
    {
        System.out.println("---------------entered the function----------");
        
        WebSockets w = Reference.w;
        if(w == null)
        {
            w = new WebSockets();
        }
        
        if(w.getTasks().isEmpty())
        {
            System.out.println("----------reloading----------");
            Task t[] = ts.getAll();
            for(int x=0; x<t.length;x++)
            {
               Tasks tmp = new Tasks(String.valueOf(t[x].getID()),String.valueOf(t[x].getID()));
               tmp.setColour(t[x].getColour());
               tmp.setDays(t[x].getDays());
               tmp.setDescription(t[x].getDescription());
               tmp.setName(t[x].getName());
               tmp.setPoints(t[x].getPoints());
               tmp.setPos(t[x].getTopPos(),t[x].getLeftPos());
               tmp.setResponsible(t[x].getResponsible());
               tmp.setStatus(t[x].getStatus());
               tmp.setComments(t[x].getComments());
               tmp.setSubTasks(t[x].getSubTasks());
               tmp.dbUpdate();
                System.out.println("Task: "+ x);
               w.getTasks().add(tmp);
               
            }
            
            w.sendAllTasks();
            System.out.println("----------done Reloading----------");
        }
    }
}
