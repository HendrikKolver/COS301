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

    public String getDescription() {
        return description;
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
    String description = "None";

    public String getTaskName() {
        
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        System.out.println("Set Task Name: "+ taskName);
        this.taskName = taskName;
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
        ArrayList<Tasks> t = Reference.w.getTasks();
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
        ArrayList<Tasks> t = Reference.w.getTasks();
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
         ArrayList<Tasks> t = Reference.w.getTasks();
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
        ArrayList<Tasks> t = Reference.w.getTasks();
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
        ArrayList<Tasks> t = Reference.w.getTasks();
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
        ArrayList<Tasks> t = Reference.w.getTasks();
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
