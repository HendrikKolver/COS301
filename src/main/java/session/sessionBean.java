/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import Reference.Project;
import java.io.Serializable;
import javax.faces.bean.SessionScoped; 
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hendrik
 */
@SessionScoped
@ManagedBean(name = "sessionBean")
public class sessionBean implements Serializable
{
    
    String username;
    boolean loggedIn;
    String projectID ;
    Project project;
    String videoChatUrl;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        
        this.project = project;
        projectID = this.project.getId();
        System.out.println("Project id : "+projectID);
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getUsername() {
        //username = "Jannie";
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVideoChatUrl() {
        return videoChatUrl;
    }

    public void setVideoChatUrl(String videoChatUrl) {
        this.videoChatUrl = videoChatUrl;
    }
    
    
}
