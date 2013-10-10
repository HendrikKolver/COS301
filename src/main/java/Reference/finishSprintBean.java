/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import session.sessionBean;

/**
 *
 * @author hendrik
 */
@ManagedBean(name = "finishSprintBean")
public class finishSprintBean {
    
    @ManagedProperty(value = "#{sessionBean}")
    private sessionBean session;

    public sessionBean getSession() {
        return session;
    }

    public void setSession(sessionBean session) {
        this.session = session;
    }
    
    public void finishSprint()
    {
        ArrayList<Tasks> t = Reference.getTasks();
        for (int i = 0; i < t.size(); i++) {
            if(session.getProjectID()==null)
                System.out.println("damb1");
            if(t.get(i)==null)
                System.out.println("damb2");
            if(t.get(i).getProjectID()==null)
                System.out.println("damb3");
            
            if(t.get(i).getProjectID().equals(session.getProjectID()))
            {
                if(t.get(i).getStatus().equals("completed"))
                {
                    t.get(i).setSprintBacklog(false);
                }
            }
             
        }
        
        //remove old burndown chart and add a new one
        session.getProject().PreviousBurndownCharts.add(session.getProject().burndownPoints);
        session.getProject().burndownPoints = new ArrayList<Integer>();
        session.getProject().burndownPoints.add(0);
        
        Reference.tasks = t;
    }
    
}
