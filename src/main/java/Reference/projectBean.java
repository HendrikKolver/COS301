/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author growthpoint
 */
@ManagedBean(name = "projectBean")
public class projectBean {
    
    
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


    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
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
            }  
        }
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        System.out.println("Setting project ID: " + projectID);
        this.projectID = projectID;
    }

    public String getEditDescription() {
        return editDescription;
    }

    public void setEditDescription(String editDescription) {
        this.editDescription = editDescription;
        
        ArrayList<Tasks> tmpTasks = Reference.tasks;
        for (int i = 0; i < tmpTasks.size(); i++) {
            if(tmpTasks.get(i).getID().equals(projectID))
            {
                System.out.println("Setting Description: " + editDescription);
                 tmpTasks.get(i).setDescription(this.editDescription);
                 break;
            }
        }
    }

    public String getEditPersonResponsible() {
        return editPersonResponsible;
    }

    public void setEditPersonResponsible(String editPersonResponsible) {
        this.editPersonResponsible = editPersonResponsible;
        ArrayList<Tasks> tmpTasks = Reference.tasks;
        for (int i = 0; i < tmpTasks.size(); i++) {
            if(tmpTasks.get(i).getID().equals(projectID))
            {
                 tmpTasks.get(i).setResponsible(this.editPersonResponsible);
                 break;
            }
        }
    }

    public String getEditTaskName() {
        return editTaskName;
    }

    public void setEditTaskName(String editTaskName) {
        this.editTaskName = editTaskName;
        ArrayList<Tasks> tmpTasks = Reference.tasks;
        for (int i = 0; i < tmpTasks.size(); i++) {
            if(tmpTasks.get(i).getID().equals(projectID))
            {
                 tmpTasks.get(i).setName(this.editTaskName);
                 break;
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public String getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(String daysLeft) {
        this.daysLeft = daysLeft;
        ArrayList<Tasks> tmpTasks = Reference.tasks;
        for (int i = 0; i < tmpTasks.size(); i++) {
            if(tmpTasks.get(i).getID().equals(projectID))
            {
                 tmpTasks.get(i).setDays(this.daysLeft);
                 break;
            }
        }
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(String storyPoints) {
        this.storyPoints = storyPoints;
        ArrayList<Tasks> tmpTasks = Reference.tasks;
        for (int i = 0; i < tmpTasks.size(); i++) {
            if(tmpTasks.get(i).getID().equals(projectID))
            {
                 tmpTasks.get(i).setPoints(this.storyPoints);
                 break;
            }
        }
    }

    public void setDescription(String description) {
        System.out.println("Set description: "+ description);
        this.description = description;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        System.out.println("Set personResponsible: "+ personResponsible);
        this.personResponsible = personResponsible;
    }
    

    public String getTaskName() {
        
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        System.out.println("Set Task Name: "+ taskName);
        this.taskName = taskName;
    }
    
    public void updateSpecificTask()
    {
        System.out.println("Updating the task");
    }
    
    public void updateDB()
    {
       System.out.println("Update DB Called"); 
       //Tasks t = new Tasks();
       ArrayList<Tasks> tmp = Reference.tasks;
       tmp.get(tmp.size()-1).setName(taskName);
       tmp.get(tmp.size()-1).setDescription(description);
       tmp.get(tmp.size()-1).setResponsible(personResponsible);
       //Reference.w.sendTasks();
    }

    

    public Tasks[] getSprintCompleted() {
        ArrayList<Tasks> t = Reference.getTasks();
        int k=0;
        for (int i = 0; i < t.size(); i++) {
            if(t.get(i).getStatus().equals("completed") && t.get(i).getSprintBacklog())
            {
                k++;
            }
            
        }
        sprintCompleted = new Tasks[k];
        k=0;
        for (int i = 0; i < t.size(); i++) {
            
            if(t.get(i).getStatus().equals("completed") && t.get(i).getSprintBacklog())
            {
                sprintCompleted[k]=t.get(i);
                k++;
            }
        }
        return sprintCompleted;
    }

    public Tasks[] getSprintInProgress() {
        ArrayList<Tasks> t = Reference.getTasks();
        int k=0;
        for (int i = 0; i < t.size(); i++) {
            if(t.get(i).getStatus().equals("inProgress") && t.get(i).getSprintBacklog())
            {
                k++;
            }
            
        }
        sprintInProgress = new Tasks[k];
        k=0;
        for (int i = 0; i < t.size(); i++) {
            
            if(t.get(i).getStatus().equals("inProgress") && t.get(i).getSprintBacklog())
            {
                sprintInProgress[k]=t.get(i);
                k++;
            }
        }
        return sprintInProgress;
    }

    public Tasks[] getSprintNotStarted() {
         ArrayList<Tasks> t = Reference.getTasks();
        int k=0;
        for (int i = 0; i < t.size(); i++) {
            if(t.get(i).getStatus().equals("notStarted") && t.get(i).getSprintBacklog())
            {
                k++;
            }
            
        }
        sprintNotStarted = new Tasks[k];
        k=0;
        for (int i = 0; i < t.size(); i++) {
            
            if(t.get(i).getStatus().equals("notStarted") && t.get(i).getSprintBacklog())
            {
                sprintNotStarted[k]=t.get(i);
                k++;
            }
        }
        return sprintNotStarted;
    }
    

    public Tasks[] getCompletedList() {
        ArrayList<Tasks> t = Reference.getTasks();
        int k=0;
        for (int i = 0; i < t.size(); i++) {
            if(t.get(i).getStatus().equals("completed"))
            {
                k++;
            }
            
        }
        completedList = new Tasks[k];
        k=0;
        for (int i = 0; i < t.size(); i++) {
            
            if(t.get(i).getStatus().equals("completed"))
            {
                completedList[k]=t.get(i);
                k++;
            }
        }
        return completedList;
    }

    public Tasks[] getInProgressList() {
        ArrayList<Tasks> t = Reference.getTasks();
        int k=0;
        for (int i = 0; i < t.size(); i++) {
            if(t.get(i).getStatus().equals("inProgress"))
            {
                k++;
            }
            
        }
        inProgressList = new Tasks[k];
        k=0;
        for (int i = 0; i < t.size(); i++) {
            
            if(t.get(i).getStatus().equals("inProgress"))
            {
                inProgressList[k]=t.get(i);
                k++;
            }
        }
        return inProgressList;
    }

    public Tasks[] getNotStartedlist() {
        ArrayList<Tasks> t = Reference.getTasks();
        int k=0;
        for (int i = 0; i < t.size(); i++) {
            if(t.get(i).getStatus().equals("notStarted"))
            {
                k++;
            }
            
        }
        notStartedlist = new Tasks[k];
        k=0;
        for (int i = 0; i < t.size(); i++) {
            
            if(t.get(i).getStatus().equals("notStarted"))
            {
                notStartedlist[k]=t.get(i);
                k++;
            }
        }
       
        return notStartedlist;
        
    }
    

  
    
    
    
}
