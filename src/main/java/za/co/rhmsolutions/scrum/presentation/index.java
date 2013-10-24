package za.co.rhmsolutions.scrum.presentation;


import Reference.Project;
import Reference.Reference;
import Reference.Tasks;
import Reference.User;
import Reference.WebSockets;
import java.util.ArrayList;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import session.sessionBean;
import za.co.rhmsolutions.scrum.business.boundary.AppUserService;
import za.co.rhmsolutions.scrum.business.boundary.AuditService;
import za.co.rhmsolutions.scrum.business.boundary.ProjectService;
import za.co.rhmsolutions.scrum.business.boundary.TaskService;
import za.co.rhmsolutions.scrum.business.entity.AppUser;
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
    
    @Inject
    ProjectService ps;
    
    @Inject
    AppUserService as;
    
    @Inject
    AuditService aus;
 
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
    
    /*Project Variables*/
    String projectID;
    String projectIDtemp;
    String projectName;
    String videoUrl;
    ArrayList<String> usernames;
    ArrayList<ArrayList<Integer>> PreviousBurndownCharts = new ArrayList<ArrayList<Integer>> ();
    ArrayList<Integer> burndownPoints = new ArrayList<Integer>();
    int rowCount;
    
    /*User Variables*/
    String user = "";
    String pass = "";
    String Name = "";
    String lastname = "";
    
    int auditCount = -1;

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public ArrayList<ArrayList<Integer>> getPreviousBurndownCharts() {
        return PreviousBurndownCharts;
    }

    public void setPreviousBurndownCharts(ArrayList<ArrayList<Integer>> PreviousBurndownCharts) {
        this.PreviousBurndownCharts = PreviousBurndownCharts;
    }

    public ArrayList<Integer> getBurndownPoints() {
        return burndownPoints;
    }

    public void setBurndownPoints(ArrayList<Integer> burndownPoints) {
        this.burndownPoints = burndownPoints;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getProjectname() {
        return projectName;
    }

    public void setProjectname(String projectname) {
        this.projectName = projectname;
    }

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    //Creates new task for all new changes using values in w. Not for production purposes
    public void createTask()
    {
        try
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
        catch (Exception e)
        {
            System.out.println("Warning: index.java, createTask");
        } 
                
        
    }
    
    //Update task using value of updateID
    public void update()
    {
        try
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
            upateAudit();
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, update");
        }   
    }
    
    public void updateLastTask()
    {
        try
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

            upateAudit();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, updateLastTask");
        } 
    }
    
    //Creates a template task using default values and returns id as String
    public void updateID()
    {  
        try
        {
            long id;
            id = ts.getID();
            ID = String.valueOf(id);
            System.out.println("String id: " + ID);
            WebSockets w = Reference.w;
            w.addTask(ID);
            w.sendTasks();

            upateAudit();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, updateID");
        }     
    }
    
    public void delete()
    {
        try
        {
            WebSockets w = Reference.w;

            long id;
            id = Long.valueOf(deleteID).longValue();

            ts.delete(id);
            System.out.println("Delete task " + id);    

            upateAudit();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, delete");
        }   
    }
    
    public void getAllTasks()
    {
        try
        {
            WebSockets w = Reference.w;
            if(w == null)
            {
                w = new WebSockets();
                Reference.w = w;
            }

            if(w.getTasks().isEmpty())
            {
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
                       System.out.println("Failed");
                       tmp.setPos("0","0");
                   }else
                       tmp.setPos(t[x].getTopPos(),t[x].getLeftPos());

                   tmp.setResponsible(t[x].getResponsible());
                   tmp.setStatus(t[x].getStatus());
                   tmp.setComments(t[x].getComments());
                   tmp.setSubTasks(t[x].getSubTasks());
                   tmp.setSprintBacklog(t[x].isSprintBacklog());
                   tmp.setProjectID(t[x].getProjectID());
                   tmp.dbUpdate();
                    System.out.println("Task: "+ x);
                   w.getTasks().add(tmp);

                }

                w.sendAllTasks();
            }    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, getAllTasks");
        }  
    }
    
    public boolean isInteger(String s) 
    {
        try { 
            Double.parseDouble(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }
    
    public void createProject()
    {
        try
        {
            System.out.println("Create Project Clicked!");
            long id = ps.create(projectName);

            Project tmp = new Project();
            tmp.setId(String.valueOf(id));
            tmp.setProjectName(projectName);
            double tmpNum = Math.random()*System.currentTimeMillis();
            tmpNum = Math.round(tmpNum);
            String roomID = "#meeting-roomid-"+id+(int)tmpNum+"-"+id;
            tmp.setVideoUrl(roomID);

            ps.setRoomID(id, roomID);
            //tmp.addUser(session.getUsername());
            tmp.addUser("admin"); 
            tmp.update = true;

            Reference.projects.add(tmp);
            projectUpdate();

            upateAudit();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, createProject");
        } 
    }

    public void projectUpdate()
    {
        try
        {
            System.out.println("UPDATE PROJECT!");
            WebSockets w = Reference.w;

            for(int x=0; x< Reference.getProjects().size();x++)
            {
                System.out.println(Reference.getProjects().get(x).isUpdate());
                if (Reference.getProjects().get(x).isUpdate())
                {
                     projectID = Reference.getProjects().get(x).getId();
                     projectName = Reference.getProjects().get(x).getProjectName();
                     videoUrl = Reference.getProjects().get(x).getVideoUrl();
                     usernames = Reference.getProjects().get(x).getUsernames();
                     PreviousBurndownCharts = Reference.getProjects().get(x).getPreviousBurndownCharts();
                     burndownPoints = Reference.getProjects().get(x).getBurndownPoints();
                     rowCount = Reference.getProjects().get(x).getRowCount();

                     long id;
                     id = Long.valueOf(projectID).longValue();

                     ps.update(id, projectName, usernames, PreviousBurndownCharts, burndownPoints);

                     System.out.println("Updated Project " + projectID + " name: " + projectName);
                     Reference.getProjects().get(x).dbUpdate();
                }
            }

            upateAudit();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, projectUpdate");
        } 
    }
    
    public void addProjects()
    {
        try
        {
            System.out.println("Called the function");
            if(Reference.getProjects().isEmpty())
            {
                 System.out.println("adding the projects");
                za.co.rhmsolutions.scrum.business.entity.Project[] proj = ps.getAllProjects();
                System.out.println("HERE!!!");

                for (int i = 0; i < proj.length; i++) 
                {
                    Project tmp = new Project();
                    String id = "" + proj[i].getId();
                    //tmp.addUser("admin"); 
                    tmp.setId(id);
                    tmp.setProjectName(proj[i].getName());
                    tmp.setVideoUrl(proj[i].getRoom_ID());
                    //tmp.setVideoUrl(videoUrl);

                    for (int j = 0; j < proj[i].getUsernames().size(); j++) 
                    {
                        tmp.addUser(proj[i].getUsernames().get(j));
                    }

                    //call add sprints func
                    ArrayList<ArrayList<Integer>> tempCharts = ps.getBurndownCharts(proj[i].getId());

                    if (tempCharts.size() > 0)
                    {
                        int size = tempCharts.size();
                        tmp.setBurndownPoints(tempCharts.get(size - 1));
                        tempCharts.remove(size - 1);

                        tmp.setPreviousBurndownCharts(tempCharts);
                    }
                    else
                    {
                        System.out.println("WARNING! Number of burndown charts = 0");
                    }

                    Reference.projects.add(tmp);
                }
            }    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, addProjects");
        } 
    }
    
    public void deleteProjects()
    {
        try
        {
            long id;
            id = Long.valueOf(projectID).longValue();

            ps.delete(id);
            System.out.println("Delete project " + id);  

            upateAudit();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, deleteProjects");
        } 
    }
    
    public void addUser()
    {
        try
        {
            System.out.println("Create User Clicked!");
            WebSockets w = Reference.w;

            int size = Reference.getUsernames().size();

            String username = Reference.getUsernames().get(size-1).getUsername();
            String password = Reference.getUsernames().get(size-1).getPassword();
            String newName = Reference.getUsernames().get(size-1).getName();
            String surname = Reference.getUsernames().get(size-1).getSurname();

            as.createUser(newName, surname, username,password);

            upateAudit();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, addUser");
        } 
    }
    
    public void getUsers()
    {
        try
        {
            if(Reference.getUsernames().isEmpty())
            {
                System.out.println("Adding The Users");
                AppUser[] users = as.getAllAppUsers();

                for (int i = 0; i < users.length; i++) 
                {
                    User tmp = new User(users[i].getUsername(), users[i].getPassword(), users[i].getName(), users[i].getSurname());
                    Reference.usernames.add(tmp);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, getUsers");
        } 
    }
    
    public void updateUsers()
    {
        try
        {
            System.out.println("UPDATE USERS!");
            WebSockets w = Reference.w;

            for(int x=0; x< Reference.getUsernames().size();x++)
            {
                System.out.println(Reference.getUsernames().get(x).isUpdate());

                if (Reference.getUsernames().get(x).isUpdate())
                {
                    this.user = Reference.getUsernames().get(x).getUsername();
                    this.Name = Reference.getUsernames().get(x).getName();
                    this.pass = Reference.getUsernames().get(x).getPassword();
                    this.lastname = Reference.getUsernames().get(x).getSurname();

                    as.updateUser(Name, lastname, user, pass);

                    System.out.println("Updated User: " + user);
                    Reference.getUsernames().get(x).update();
                }
            }

            upateAudit();    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, updateUsers");
        }  
    }
    
    public void upateAudit()
    {
        try
        {
            if (this.auditCount == -1)
            {
                auditCount = aus.countLogs();
            }

            int tableSize = aus.countLogs();

            if (Reference.audit.size() > tableSize - auditCount)
            {

               int count = Reference.audit.size() - (tableSize - auditCount);
               int loop = Reference.audit.size() - count;

               for (int i = Reference.audit.size() - 1; i > Reference.audit.size() - count; i--) 
               {

                   String log = Reference.audit.get(i);

                   if(log.length()<4000)
                    aus.create(log);
                   else
                   {
                     log = log.substring(0, 4000); 
                     aus.create(log);       
                   }
               }
            }    
        }
        catch (Exception e)
        {
            System.out.println("Warning: index.java, updateAudit");
        } 
    }
}
