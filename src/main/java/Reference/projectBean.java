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
