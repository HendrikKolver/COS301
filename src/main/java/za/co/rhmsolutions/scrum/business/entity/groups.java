/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Richard O'Brien
 */
@Entity
public class groups
{
    @Id
    @GeneratedValue
    private Long id;
    
    String GroupId;
    String Username;

/**
 * The default constructor method of the groups Entity class needed for JPA.
 * 
 */
    public groups() 
    {
        
    }

/**
 * A constructor method of the groups Entity class.
 * 
 * @param  GroupId  The String GroupId of the user to define his privilege (admin, user)
 * @param  Username The String Username of the user whose privilege is defined
 */
    public groups(String GroupId, String Username) 
    {
        this.GroupId = GroupId;
        this.Username = Username;
    }

/**
 * Returns a String of the GroupID. 
 * The Getter for the GroupId attribute 
 *
 * @return      the String GroupId      
 */
    public String getGroupId() {
        return GroupId;
    }

/**
 * Sets the String GroupId of the group relation. 
 * The Setter for the GroupId attribute 
 *
 * @param  id the String GroupId of the groups relation    
 */    
    public void setGroupId(String GroupId) {
        this.GroupId = GroupId;
    }

/**
 * Returns a String of the Username. 
 * The Getter for the Username attribute 
 *
 * @return      the String Username      
 */
    public String getUsername() {
        return Username;
    }

/**
 * Sets the String Username of the group relation. 
 * The Setter for the Username attribute 
 *
 * @param  id the String Username of the groups relation    
 */   
    public void setUsername(String Username) {
        this.Username = Username;
    }

/**
 * Returns a Long of the id. 
 * The Getter for the id attribute 
 *
 * @return      the Long id      
 */
    public Long getId() {
        return id;
    }

/**
 * Sets the id of the group relation. 
 * The Setter for the id attribute 
 *
 * @param  id the Long id of the groups relation    
 */          
    public void setId(Long id) {
        this.id = id;
    }

}
