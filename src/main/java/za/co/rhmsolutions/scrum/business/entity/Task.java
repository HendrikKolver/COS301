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
    boolean sprintBacklog;
    String projectID;
    boolean deleted;

    public long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopPos(String topPos) {
        this.topPos = topPos;
    }

    public void setLeftPos(String leftPos) {
        this.leftPos = leftPos;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setSprintBacklog(boolean sprintBacklog) {
        this.sprintBacklog = sprintBacklog;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public boolean isSprintBacklog() {
        return sprintBacklog;
    }

    public String getProjectID() {
        return projectID;
    }

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
            String status, String description, String responsible, String points, String days, String colour, String comments, String subTasks, String projectID, boolean sprintBacklog) {
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
        this.sprintBacklog = sprintBacklog;
        this.projectID = projectID;
        this.deleted = false;
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
