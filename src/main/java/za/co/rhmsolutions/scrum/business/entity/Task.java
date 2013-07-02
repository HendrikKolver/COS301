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

    String name;

    
    String topPos;
    String leftPos;
    String status;
    String description;
    String responsible;
    String points;
    String days;
    String colour;
    
    //private long projectID;

    /*needed for JPA*/
    public Task()
    {
        name = "TaskName";
        topPos = "topPos";
        leftPos = "leftPos";
        status = "status";
        description = "description";
        responsible = "responsible";
        points = "0";
        days = "0";
        colour = "colour";
        //projectID = 1;
    }

    public Task(String name) {
        this.name = name;
    }
    
    public Task(String name, String topPos, String leftPos, 
            String status, String description, String responsible, String points, String days, String colour) {
        System.out.println("name: "+name);
        this.name = name;
        this.topPos = topPos;
        this.leftPos = leftPos;
        this.status = status;
        this.description = description;
        this.responsible = responsible;
        this.points = points;
        this.days = days;
        this.colour = colour;
    }
    
    public long getID()
    {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTopPos() {
        return topPos;
    }

    public String getLeftPos() {
        return leftPos;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsible() {
        return responsible;
    }

    public String getPoints() {
        return points;
    }

    public String getDays() {
        return days;
    }

    public String getColour() {
        return colour;
    }
}
