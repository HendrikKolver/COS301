/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import Injection.ScriptCheck;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import session.sessionBean;

/**
 *
 * @author hendrik
 */

@ManagedBean(name = "adminBean")
public class AdminBean {
    @ManagedProperty(value = "#{sessionBean}")
    private sessionBean session;

    public sessionBean getSession() {
        return session;
    }
    
    public void setSession(sessionBean session) {
        this.session = session;
    }
    
    String projectToAddName = "";
    String userToAddUsername = "";
    String userToAddPassword = "";
    String userToAddName = "";
    String userToAddSurname = "";
    String projectToEditName ="";
    String projectToEditID = "";
    String oldUsername = "";
    ScriptCheck s= new ScriptCheck();
    String projectToDeleteID = "";

    public String getProjectToDeleteID() {
        return projectToDeleteID;
    }

    public void setProjectToDeleteID(String projectToDeleteID) {
        for (int i = 0; i < Reference.getProjects().size(); i++) {
            if(Reference.getProjects().get(i).getId().equals(projectToDeleteID))
            {
                Calendar cal = Calendar.getInstance();
                cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                Reference.audit.add(sdf.format(cal.getTime())+", "+ "deletingProject, project id: "+projectToDeleteID+", user: "+ session.getUsername());
                
                Reference.getProjects().remove(i);
                break;
            }
        }
        this.projectToDeleteID = projectToDeleteID;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        oldUsername = s.removeScript(oldUsername);
        this.oldUsername = oldUsername;
    }

    public String getProjectToEditID() {
        return projectToEditID;
    }

    public void setProjectToEditID(String projectToEditID) {
        projectToEditID = s.removeScript(projectToEditID);
        this.projectToEditID = projectToEditID;
    }

    public String getProjectToEditName() {
        return projectToEditName;
    }

    public void setProjectToEditName(String projectToEditName) {
        projectToEditName = s.removeScript(projectToEditName);
        this.projectToEditName = projectToEditName;
    }

    public String getProjectToAddName() {
        return projectToAddName;
    }

    public void setProjectToAddName(String projectToAddName) {
        projectToAddName = s.removeScript(projectToAddName);
        this.projectToAddName = projectToAddName;
    }

    public String getUserToAddName() {
        return userToAddName;
    }

    public void setUserToAddName(String userToAddName) {
        userToAddName = s.removeScript(userToAddName);
        this.userToAddName = userToAddName;
    }

    public String getUserToAddPassword() {
        return userToAddPassword;
    }

    public void setUserToAddPassword(String userToAddPassword) {
        userToAddPassword = s.removeScript(userToAddPassword);
        this.userToAddPassword = userToAddPassword;
    }

    public String getUserToAddSurname() {
        return userToAddSurname;
    }

    public void setUserToAddSurname(String userToAddSurname) {
        userToAddSurname = s.removeScript(userToAddSurname);
        this.userToAddSurname = userToAddSurname;
    }

    public String getUserToAddUsername() {
        return userToAddUsername;
    }

    public void setUserToAddUsername(String userToAddUsername) {
        userToAddUsername = s.removeScript(userToAddUsername);
        this.userToAddUsername = userToAddUsername;
    }
    

    public void editProject()
    {
        try
        {
        for (int i = 0; i < Reference.getProjects().size(); i++) {
            if(Reference.getProjects().get(i).getId().equals(projectToEditID))
            {
                Calendar cal = Calendar.getInstance();
                cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                Reference.audit.add(sdf.format(cal.getTime())+", "+ "Set project name, project id: "+projectToEditID+",project name: "+projectToEditName+" user: "+ session.getUsername());
                Reference.getProjects().get(i).setProjectName(projectToEditName);
            }
        }
        }catch(Exception e)
        {
            System.out.println("Admin bean.java, edit project");
        }
    }
    
    public void updateUser()
    {
        try{
            for (int i = 0; i < Reference.getUsernames().size(); i++) {
                if(Reference.getUsernames().get(i).getUsername().equals(oldUsername))
                {
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
                    Reference.audit.add(sdf.format(cal.getTime())+", "+ "Update user,"+oldUsername+","+userToAddName+","+userToAddPassword+","+userToAddSurname+" user: "+ session.getUsername());
                    Reference.getUsernames().get(i).setName(userToAddName);
                    Reference.getUsernames().get(i).setPassword(userToAddPassword);
                    Reference.getUsernames().get(i).setSurname(userToAddSurname);
                }
            }
        }catch(Exception e)
        {
            System.out.println("Admin bean.java, update user");
        }
    }
    
    public void addUser()
    {
        //callToDbToaddUser;
        try{
            Reference.usernames.add(new User(userToAddUsername,userToAddPassword,userToAddName,userToAddSurname));
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 'at' HH:mm:ss");
            Reference.audit.add(sdf.format(cal.getTime())+", "+ "Add user,"+userToAddUsername+","+userToAddName+","+userToAddPassword+","+userToAddSurname+" user: "+ session.getUsername());
                    
        }catch(Exception e)
        {
            System.out.println("Admin bean.java, add user");
        }
    }
    
    public void addProject()
    {
        //calltoDbToAddProject
    }
    
    public Object[] getAllUsernames()
    {
        //get all usernames from db
        
        return Reference.getUsernames().toArray();
    }
    
    public Object[] getAllProjects()
    {
        return Reference.projects.toArray();
    }
    
    
    
}
