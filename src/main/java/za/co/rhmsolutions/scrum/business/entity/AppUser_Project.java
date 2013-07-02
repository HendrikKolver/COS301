/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Richard
 */
@Entity
public class AppUser_Project 
{
    /*@Id
    @ManyToOne (cascade=CascadeType.PERSIST) AppUser appUser;
    @Id
    @ManyToOne (cascade=CascadeType.PERSIST) Project project;

    //needed for jpa
    public AppUser_Project() 
    {
        
    }

    public AppUser_Project(AppUser appUser, Project project) {
        this.appUser = appUser;
        this.project = project;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }*/
    
    @Id
    long userID;
    @Id
    long projectID;

    //needed for jpa
    public AppUser_Project() 
    {
        
    }

    public AppUser_Project(long userID, long projectID) 
    {
        this.userID = userID;
        this.projectID = projectID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }
    
    
    
    
}
