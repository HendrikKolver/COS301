/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

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

    public String getProjectToAddName() {
        return projectToAddName;
    }

    public void setProjectToAddName(String projectToAddName) {
        this.projectToAddName = projectToAddName;
    }

    public String getUserToAddName() {
        return userToAddName;
    }

    public void setUserToAddName(String userToAddName) {
        this.userToAddName = userToAddName;
    }

    public String getUserToAddPassword() {
        return userToAddPassword;
    }

    public void setUserToAddPassword(String userToAddPassword) {
        this.userToAddPassword = userToAddPassword;
    }

    public String getUserToAddSurname() {
        return userToAddSurname;
    }

    public void setUserToAddSurname(String userToAddSurname) {
        this.userToAddSurname = userToAddSurname;
    }

    public String getUserToAddUsername() {
        return userToAddUsername;
    }

    public void setUserToAddUsername(String userToAddUsername) {
        this.userToAddUsername = userToAddUsername;
    }
    
    
    public void addUser()
    {
        //callToDbToaddUser;
    }
    
    public void addProject()
    {
        //calltoDbToAddProject
    }
    
    public String[] getAllUsernames()
    {
        //get all usernames from db
        return null;
    }
    
    public Object[] getAllProjects()
    {
        return Reference.projects.toArray();
    }
    
    
    
}
