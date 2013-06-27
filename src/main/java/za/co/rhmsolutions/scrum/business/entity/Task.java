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
 * @author Richard
 * Boundaries are able to access entities directly
 * Anything that is persistent is placed in entity package
 * Entity: Persistent
 */
@Entity
public class Task //persistent
{
    @Id
    @GeneratedValue
    private long id;
    
    private String name;

    /*needed for JPA*/
    public Task()
    {
    }

    public Task(String name) {
        this.name = name;
    }   
}
