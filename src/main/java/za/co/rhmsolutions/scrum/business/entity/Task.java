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
    String comments;
    String subTasks;

    //private long projectID;

    public void setComments(String comments) {
        this.comments = comments;
    }

    

    public void setSubTasks(String subTasks) {
        this.subTasks = subTasks;
    }
    

    /*needed for JPA*/
    public Task()
    {
        name = "TaskName";
        topPos = "topPos";
        leftPos = "leftPos";
        status = "status";
        description = "description";
        responsible = "responsible";
        points = "points";
        days = "days";
        colour = "colour";
        comments = "";
        subTasks = "";
    }

    public Task(String name) {
        this.name = name;
    }
    
    public Task(String name, String topPos, String leftPos, 
            String status, String description, String responsible, String points, String days, String colour, String comments, String subTasks) {
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
        this.comments = comments;
        this.subTasks = subTasks;
    }
    
    public String getSubTasks() {
        return subTasks;
    }
    
    public String getComments() {
        return comments;
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
