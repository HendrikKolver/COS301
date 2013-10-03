package za.co.rhmsolutions.scrum.presentation;


import Reference.Reference;
import Reference.Tasks;
import Reference.WebSockets;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import session.sessionBean;
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
    @ManagedProperty(value = "#{sessionBean}")
    private sessionBean session;

    public sessionBean getSession() {
        return session;
    }

    public void setSession(sessionBean session) {
        this.session = session;
    }
    
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
    boolean sprintBacklog;
    String projectID;

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
        //System.out.println("Getting update ID: " + updateID);
        return updateID;
    }

    public void setUpdateID(String updateID) {
        //System.out.println("Setting update ID: " + updateID);
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
                sprintBacklog = w.getTasks().get(x).getSprintBacklog();
                projectID = w.getTasks().get(x).getProjectID();

                ts.create(name, topPos, leftPos, status, description, responsible, points, days, colour, comments, subTasks, projectID, sprintBacklog);
                
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
                sprintBacklog = w.getTasks().get(x).getSprintBacklog();
                projectID = w.getTasks().get(x).getProjectID();
                
                long id;
                id = Long.valueOf(updateID).longValue();
                
                ts.update(id, name, topPos, leftPos, status, description, responsible, points, days, colour, comments, subTasks, projectID, sprintBacklog);
                System.out.println("Updated task " + id);
                w.getTasks().get(x).dbUpdate();
                
                break;
            }
        }
    }
    
    public void updateLastTask()
    {
        
        System.out.println(Reference.getTasks().size());
        int x = Reference.getTasks().size()-1;
        name = Reference.getTasks().get(x).getName();
        topPos = Reference.getTasks().get(x).getTopPos();
        leftPos = Reference.getTasks().get(x).getLeftPos();
        status = Reference.getTasks().get(x).getStatus();
        description = Reference.getTasks().get(x).getDescription();
        responsible = Reference.getTasks().get(x).getResponsible();
        points = Reference.getTasks().get(x).getPoints();
        days = Reference.getTasks().get(x).getDays();
        colour = Reference.getTasks().get(x).getColour();
        comments = Reference.getTasks().get(x).getComments();
        subTasks = Reference.getTasks().get(x).getSubTasks();
        sprintBacklog = Reference.getTasks().get(x).getSprintBacklog();
        projectID = Reference.getTasks().get(x).getProjectID();

        long id;
        id = Long.valueOf(Reference.getTasks().get(x).getID());

        ts.update(id, name, topPos, leftPos, status, description, responsible, points, days, colour, comments, subTasks, projectID, sprintBacklog);
        System.out.println("Updated task " + id);
        Reference.getTasks().get(x).dbUpdate();

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
            Reference.w = w;
        }
        
        if(w.getTasks().isEmpty())
        {
            System.out.println("----------reloading----------");
            Task t[] = ts.getAll();
            for(int x=0; x<t.length;x++)
            {
               Tasks tmp = new Tasks(String.valueOf(t[x].getID()),String.valueOf(t[x].getID()));
               tmp.setColour(t[x].getColour());
               if(t[x].getDays()==null || !isInteger(t[x].getDays()))
               {
                   tmp.setDays("0");
               }else
                tmp.setDays(t[x].getDays());
               tmp.setDescription(t[x].getDescription());
               tmp.setName(t[x].getName());
               if(t[x].getPoints()==null || !isInteger(t[x].getPoints()))
               {
                   tmp.setPoints("0");
               }
               else
                tmp.setPoints(t[x].getPoints());
               
               if(t[x].getTopPos()==null ||t[x].getLeftPos()==null || !isInteger(t[x].getTopPos()) || !isInteger(t[x].getLeftPos()))
               {
                   tmp.setPos("0","0");
               }else
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
    
    public boolean isInteger(String s) 
    {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }
}
