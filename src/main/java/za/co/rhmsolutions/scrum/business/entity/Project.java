/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.entity;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Richard O'Brien
 */
@Entity
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "Project_ID")
    private Long id;
    
    String name;
    ArrayList<String> usernames;
    boolean deleted;  
    String Room_ID;

/**
 * The default constructor method of the Task Entity class needed for JPA.
 * 
 */
    public Project() 
    {
        
    }
    
/**
 * The constructor method of the Task Entity class.
 * 
 * @param  name  The string name of the Project
 */
    public Project(String name) {
        this.name = name;
        usernames = new ArrayList<String>();
        deleted = false;
    }

/**
 * Returns the boolean deleted of the Project. 
 * The Getter for the deleted attribute 
 *
 * @return      the boolean deleted     
 */    
    public boolean isDeleted() {
        return deleted;
    }

/**
 * Sets the deleted flag of the task. 
 * The Setter for the deleted attribute 
 *
 * @param  deleted the boolean deleted flag of the Project.    
 */    
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

/**
 * Returns the String name of the Project. 
 * The Getter for the name attribute 
 *
 * @return      the String name    
 */     
    public String getName() {
        return name;
    }
    
/**
 * Sets the String name of the Project. 
 * The Setter for the name attribute 
 *
 * @param  name the String name of the Project    
 */ 
    public void setName(String name) {
        this.name = name;
    }
    
/**
 * Returns the Project ID. 
 * The Getter for the ID attribute 
 *
 * @return      the ID      
 */
    public Long getId() {
        return id;
    }
    
/**
 * Sets the id of the Project. 
 * The Setter for the id attribute 
 *
 * @param  id the long id of the Project    
 */ 
    public void setId(Long id) {
        this.id = id;
    }

/**
 * Returns the Array of Usernames allocated to the project. 
 * The Getter for the usernames attribute 
 *
 * @return      the ArrayList of usernames       
 */    
    public ArrayList<String> getUsernames() {
        return usernames;
    }
  
/**
 * Sets the usernames of AppUsers assigned to the Project. 
 * The Setter for the usernames attribute 
 *
 * @param  usernames the ArrayList usernames of the Project    
 */     
    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

/**
 * Returns the Project Room_ID ID. 
 * The Getter for the Room_ID attribute 
 *
 * @return      the String Room_ID      
 */    
    public String getRoom_ID() {
        return Room_ID;
    }

/**
 * Sets the Room_ID of the Project video/text chat room. 
 * The Setter for the Room_ID attribute 
 *
 * @param  Room_ID the String id of the Project chat room    
 */     
    public void setRoom_ID(String Room_ID) {
        this.Room_ID = Room_ID;
    }
}
