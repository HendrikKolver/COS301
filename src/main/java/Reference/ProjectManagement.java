/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import session.sessionBean;

/**
 *
 * @author hendrik
 */

@ManagedBean(name = "projectManagementBean")
public class ProjectManagement {
    
    @ManagedProperty(value = "#{sessionBean}")
    private sessionBean session;
     

    public sessionBean getSession() {
        return session;
    }
    
    public void setSession(sessionBean session) {
        this.session = session;
    }
    
    public void doNothing()
    {
        System.out.println("doNothing");
    }
    
    String id;
    String userToAdd = "";
    String userToRemove ="";
    String projectID = "";

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getUserToAdd() {
        return userToAdd;
    }

    public void setUserToAdd(String userToAdd) {
        this.userToAdd = userToAdd;
    }

    public String getUserToRemove() {
        return userToRemove;
    }

    public void setUserToRemove(String userToRemove) {
        this.userToRemove = userToRemove;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setProjectUpdate()
    {
        try{
            session.getProject().setUpdate(true);
            for (int i = 0; i < Reference.projects.size(); i++) {
                if(Reference.projects.get(i).getId().equals(session.getProject().getId()))
                    Reference.projects.get(i).update = true; 
        }
        }catch(Exception e)
        {
            System.out.println("ProjectManagement.java, setProjectUpdate()");
        }
    }
    
    public void addUser()
    {
        try
        {
            for (int i = 0; i < Reference.projects.size(); i++) {
                if( Reference.projects.get(i).getId().equals(projectID))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "Add user to project, projectID: "+projectID+" usertoAdd: "+userToAdd+ " user: "+ session.getUsername());
                
                    Reference.projects.get(i).addUser(userToAdd);
                }
            }
        }catch(Exception e)
        {
            System.out.println("ProjectManagement.java, addUser()");
        }
    }
    
    public void removeUser()
    {
        try
        {
            for (int i = 0; i < Reference.projects.size(); i++) {
                    if( Reference.projects.get(i).getId().equals(projectID))
                    {
                        Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "remove user from project, projectID: "+projectID+" usertoremove: "+userToRemove+ " user: "+ session.getUsername());
                        Reference.projects.get(i).removeUser(userToRemove);
                    }
            } 
       }catch(Exception e)
        {
            System.out.println("ProjectManagement.java, removeUser()");
        }
    }
    
    
    
    public void changeSessionProject()
    {
        try{
        System.out.println("setting: " + id);
        for (int i = 0; i < Reference.projects.size(); i++) {
            {
                if(Reference.projects.get(i).id.equals(id))
                {
                    //set session variable
                    session.setProject(Reference.projects.get(i));
                    break;
                    
                }
            }
            
        }
        }catch(Exception e)
        {
            System.out.println("ProjectManagement.java, changeSessionProject()");
        }
    }
}
