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

    //needed for jpa
    public groups() 
    {
        
    }

    public groups(String GroupId, String Username) 
    {
        this.GroupId = GroupId;
        this.Username = Username;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String GroupId) {
        this.GroupId = GroupId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUSERNAME(String Username) {
        this.Username = Username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
