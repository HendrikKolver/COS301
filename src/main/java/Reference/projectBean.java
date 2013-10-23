/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import Injection.ScriptCheck;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import session.sessionBean;

/**
 *
 * @author growthpoint
 */
@ManagedBean(name = "projectBean")
public class projectBean {
    @ManagedProperty(value = "#{sessionBean}")
    private sessionBean session;

    public sessionBean getSession() {
        return session;
    }

    public void setSession(sessionBean session) {
        this.session = session;
    }
    
    Tasks notStartedlist[];
    Tasks inProgressList[];
    Tasks completedList[];
    Tasks sprintNotStarted[];
    Tasks sprintInProgress[];
    Tasks sprintCompleted[];
    String taskName ="A dummy Task name";
    String personResponsible = "Unnasigned";
    String description ="None";
    String editTaskName ="A dummy Task name";
    String editPersonResponsible = "Unnasigned";
    String editDescription ="None";
    String storyPoints ="0";
    String daysLeft ="0";
    String projectID = "";
    String taskID ="";
    ScriptCheck s= new ScriptCheck();

    public Object[] getAllProjects()
    {
        Object[] tmp = null;
        try{
            int tmpCounter= 0;
            for (int i = 0; i < Reference.projects.size(); i++) {
                for(int x=0; x<Reference.projects.get(i).usernames.size();x++)
                {
                    if(Reference.projects.get(i).usernames.get(x).equals(session.getUsername()))
                    {
                        tmpCounter++;
                    }
                }   
            }
            tmp = new Object[tmpCounter];
            tmpCounter = 0;
                for (int i = 0; i < Reference.projects.size(); i++) {
                    for(int x=0; x<Reference.projects.get(i).usernames.size();x++)
                    {
                        if(Reference.projects.get(i).usernames.get(x).equals(session.getUsername()))
                        {
                            tmp[tmpCounter] = Reference.projects.get(i);
                            tmpCounter++;
                        }
                    }   
                }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, getAllProjects()");
        }
        
        return tmp;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        try{
            
            taskID = s.removeScript(taskID);
            this.taskID = taskID;
            for (int i = 0; i < Reference.getTasks().size(); i++) {
                if(Reference.getTasks().get(i).getID().equals(taskID))
                {
                    if(Reference.getTasks().get(i).getSprintBacklog())
                    {
                        Reference.getTasks().get(i).setSprintBacklog(false);
                    }else
                    {
                        Reference.getTasks().get(i).setSprintBacklog(true);
                    }
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "taskid Update, projectID,"+session.getProjectID()+" user: "+ session.getUsername());
             
                }  
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, setTaskID()");
        }
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        
        projectID = s.removeScript(projectID);
        this.projectID = projectID;
    }

    public String getEditDescription() {
        return editDescription;
    }

    public void setEditDescription(String editDescription) {
        try{
            editDescription = s.removeScript(editDescription);
            this.editDescription = editDescription;

            ArrayList<Tasks> tmpTasks = Reference.tasks;
            for (int i = 0; i < tmpTasks.size(); i++) {
                if(tmpTasks.get(i).getID().equals(projectID))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "Edit task description, value, "+editDescription+" projectID,"+session.getProjectID()+" user: "+ session.getUsername());

                    tmpTasks.get(i).setDescription(this.editDescription);
                    break;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, setEditDescription()");
        }
    }

    public String getEditPersonResponsible() {
        return editPersonResponsible;
    }

    public void setEditPersonResponsible(String editPersonResponsible) {
        try{
            editPersonResponsible = s.removeScript(editPersonResponsible);

            this.editPersonResponsible = editPersonResponsible;
            ArrayList<Tasks> tmpTasks = Reference.tasks;
            for (int i = 0; i < tmpTasks.size(); i++) {
                if(tmpTasks.get(i).getID().equals(projectID))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "Edit person responsible, value, "+editPersonResponsible+" projectID,"+session.getProjectID()+" user: "+ session.getUsername());

                    tmpTasks.get(i).setResponsible(this.editPersonResponsible);
                    break;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, setEditPersonResponsible()");
        }
    }

    public String getEditTaskName() {
        return editTaskName;
    }

    public void setEditTaskName(String editTaskName) {
        try{
            editTaskName = s.removeScript(editTaskName);
            this.editTaskName = editTaskName;
            ArrayList<Tasks> tmpTasks = Reference.tasks;
            for (int i = 0; i < tmpTasks.size(); i++) {
                if(tmpTasks.get(i).getID().equals(projectID))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "Edit task name, value, "+editTaskName+" projectID,"+session.getProjectID()+" user: "+ session.getUsername());

                    tmpTasks.get(i).setName(this.editTaskName);
                    break;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, setEditTaskName()");
        }
    }

    public String getDescription() {
        return description;
    }

    public String getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(String daysLeft) {
        try{
            daysLeft = s.removeScript(daysLeft);
            this.daysLeft = daysLeft;
            ArrayList<Tasks> tmpTasks = Reference.tasks;
            for (int i = 0; i < tmpTasks.size(); i++) {
                if(tmpTasks.get(i).getID().equals(projectID))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "Edit days left, value, "+daysLeft+" projectID,"+session.getProjectID()+" user: "+ session.getUsername());

                    tmpTasks.get(i).setDays(this.daysLeft);
                    break;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, setDaysLeft()");
        }
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(String storyPoints) {
        try{
            storyPoints = s.removeScript(storyPoints);
            this.storyPoints = storyPoints;
            ArrayList<Tasks> tmpTasks = Reference.tasks;
            for (int i = 0; i < tmpTasks.size(); i++) {
                if(tmpTasks.get(i).getID().equals(projectID))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "Edit story points, value, "+storyPoints+" projectID,"+session.getProjectID()+" user: "+ session.getUsername());

                    
                    tmpTasks.get(i).setPoints(this.storyPoints);
                    break;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, setStoryPoints()");
        }
    }

    public void setDescription(String description) {
        description = s.removeScript(description);
        System.out.println("Set description: "+ description);
        this.description = description;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        personResponsible = s.removeScript(personResponsible);
        System.out.println("Set personResponsible: "+ personResponsible);
        this.personResponsible = personResponsible;
    }
    

    public String getTaskName() {
        
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        taskName = s.removeScript(taskName);
        System.out.println("Set Task Name: "+ taskName);
        this.taskName = taskName;
    }
    
    public void updateSpecificTask()
    {
        System.out.println("Updating the task");
    }
    
    public void updateDB()
    {
       try{
            System.out.println("Update DB Called"); 
            //Tasks t = new Tasks();
            ArrayList<Tasks> tmp = Reference.tasks;
            tmp.get(tmp.size()-1).setName(taskName);
            tmp.get(tmp.size()-1).setDescription(description);
            tmp.get(tmp.size()-1).setResponsible(personResponsible);
            tmp.get(tmp.size()-1).setProjectID(session.getProjectID());
            System.out.println("Project id for task: "+tmp.get(tmp.size()-1).getProjectID());
            //Reference.w.sendTasks();
       }catch(Exception e)
        {
            System.out.println("ProjectBean.java, updateDB()");
        }
    }

    

    public Tasks[] getSprintCompleted() {
        try{
            ArrayList<Tasks> t = Reference.getTasks();
            int k=0;
            for (int i = 0; i < t.size(); i++) {
                if(t.get(i).getStatus().equals("completed") && t.get(i).getSprintBacklog() && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    k++;
                }

            }
            sprintCompleted = new Tasks[k];
            k=0;
            for (int i = 0; i < t.size(); i++) {

                if(t.get(i).getStatus().equals("completed") && t.get(i).getSprintBacklog()  && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    sprintCompleted[k]=t.get(i);
                    k++;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, getSprintCompleted()");
        }
        return sprintCompleted;
    }

    public Tasks[] getSprintInProgress() {
        try{
            ArrayList<Tasks> t = Reference.getTasks();
            int k=0;
            for (int i = 0; i < t.size(); i++) {
                if(t.get(i).getStatus().equals("inProgress") && t.get(i).getSprintBacklog() && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    k++;
                }

            }
            sprintInProgress = new Tasks[k];
            k=0;
            for (int i = 0; i < t.size(); i++) {

                if(t.get(i).getStatus().equals("inProgress") && t.get(i).getSprintBacklog() && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    sprintInProgress[k]=t.get(i);
                    k++;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, getSprintInProgress()");
        }
        return sprintInProgress;
    }

    public Tasks[] getSprintNotStarted() {
        try{
            ArrayList<Tasks> t = Reference.getTasks();
            int k=0;
            for (int i = 0; i < t.size(); i++) {
                if(t.get(i).getStatus().equals("notStarted") && t.get(i).getSprintBacklog() && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    k++;
                }

            }
            sprintNotStarted = new Tasks[k];
            k=0;
            for (int i = 0; i < t.size(); i++) {

                if(t.get(i).getStatus().equals("notStarted") && t.get(i).getSprintBacklog() && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    sprintNotStarted[k]=t.get(i);
                    k++;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, getSprintNotStarted()");
        }
        return sprintNotStarted;
    }
    

    public Tasks[] getCompletedList() {
        try{
            ArrayList<Tasks> t = Reference.getTasks();
            int k=0;
            for (int i = 0; i < t.size(); i++) {
                if(t.get(i).getStatus().equals("completed") && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    k++;
                }

            }
            completedList = new Tasks[k];
            k=0;
            for (int i = 0; i < t.size(); i++) {

                if(t.get(i).getStatus().equals("completed") && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    completedList[k]=t.get(i);
                    k++;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, getCompletedList()");
        }
        return completedList;
    }

    public Tasks[] getInProgressList() {
        try{
            ArrayList<Tasks> t = Reference.getTasks();
            int k=0;
            for (int i = 0; i < t.size(); i++) {
                if(t.get(i).getStatus().equals("inProgress") && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    k++;
                }

            }
            inProgressList = new Tasks[k];
            k=0;
            for (int i = 0; i < t.size(); i++) {

                if(t.get(i).getStatus().equals("inProgress") && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    inProgressList[k]=t.get(i);
                    k++;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, getInProgressList()");
        }
        return inProgressList;
    }

    public Tasks[] getNotStartedlist() {
        try{
            ArrayList<Tasks> t = Reference.getTasks();
            int k=0;
            for (int i = 0; i < t.size(); i++) {
                if(t.get(i).getStatus().equals("notStarted") && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    k++;
                }

            }
            notStartedlist = new Tasks[k];
            k=0;
            for (int i = 0; i < t.size(); i++) {

                if(t.get(i).getStatus().equals("notStarted") && session.getProject().getId().equals(t.get(i).getProjectID()))
                {
                    notStartedlist[k]=t.get(i);
                    k++;
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectBean.java, getNotStartedlist()");
        }
       
        return notStartedlist;
        
    }
    

  
    
    
    
}
