/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        try{
            ArrayList<Tasks> t = Reference.getTasks();
            for (int i = 0; i < t.size(); i++) {

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
            session.getProject().setUpdate(true);
            
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
            Reference.audit.add(sdf.format(cal.getTime())+", "+ "Finished Sprint, projectID,"+session.getProjectID()+" user: "+ session.getUsername());
                    
            Reference.tasks = t;
            }catch(Exception e)
        {
            System.out.println("finishSprint.java, finishSprint()");
        }
    }
    
}
