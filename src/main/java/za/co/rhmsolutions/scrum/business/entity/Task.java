/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    


   

/**
 * The default constructor method of the Task Entity class needed for JPA.
 * 
 */
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

    public Task(String name) 
    {
        this.name = name;
    }
    
/**
 * The constructor method of the Task Entity class.
 * 
 * @param  name  The string name of the task
 * @param  topPos The string x coordinates of the task on the scrumboard
 * @param  leftpos The string y coordinates of the task on the scrumboard
 * @param  status The string status of the task (Not started, In Progress, Complete)
 * @param  description The string description of the task for further information
 * @param  responsible The string string name of the person/people/team responsible for the task
 * @param  points The string number of points assigned to the task
 * @param  days The string number of days assigned to the task
 * @param  colour The string colour of the sticky note used to represent the task
 * @param  comments Any string comments related to the task
 * @param  subTasks The string Sub Tasks of the task
 * @param  projectId The string ID of the project the task is assigned to
 * @param  sprintBacklog A boolean flag to indicate if the task is currently in a sprint
 */
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
    
/**
 * Returns a String of Sub Tasks. 
 * The Getter for the subTasks attribute 
 *
 * @return      the String of Sub Tasks      
 */
    public String getSubTasks() {
        return subTasks;
    }
    
/**
 * Returns a String of the comments. 
 * The Getter for the subTasks attribute 
 *
 * @return      the String of comments      
 */
    public String getComments() {
        return comments;
    }
    
/**
 * Returns the task ID. 
 * The Getter for the ID attribute 
 *
 * @return      the ID      
 */
    public long getID()
    {
        return id;
    }

/**
 * Returns the String name of the task. 
 * The Getter for the name attribute 
 *
 * @return      the ID      
 */
    public String getName() {
        return name;
    }

/**
 * Returns the String topPos of the task. 
 * The Getter for the topPos attribute 
 *
 * @return      the String topPos      
 */    
    public String getTopPos() {
        return topPos;
    }

/**
 * Returns the String leftPos of the task. 
 * The Getter for the leftPos attribute 
 *
 * @return      the String leftPos      
 */      
    public String getLeftPos() {
        return leftPos;
    }

/**
 * Returns the String status of the task. 
 * The Getter for the status attribute 
 *
 * @return      the String status     
 */      
    public String getStatus() {
        return status;
    }

/**
 * Returns the String description of the task. 
 * The Getter for the description attribute 
 *
 * @return      the String status     
 */      
    public String getDescription() {
        return description;
    }

/**
 * Returns the String responsible of the task. 
 * The Getter for the responsible attribute 
 *
 * @return      the String responsible     
 */      
    public String getResponsible() {
        return responsible;
    }

/**
 * Returns the String points of the task. 
 * The Getter for the points attribute 
 *
 * @return      the String points     
 */      
    public String getPoints() {
        return points;
    }

/**
 * Returns the String days of the task. 
 * The Getter for the days attribute 
 *
 * @return      the String days     
 */      
    public String getDays() {
        return days;
    }

/**
 * Returns the String colour of the task. 
 * The Getter for the colour attribute 
 *
 * @return      the String colour     
 */      
    public String getColour() {
        return colour;
    }
    
/**
 * Returns the String projectID of the task. 
 * The Getter for the projectID attribute 
 *
 * @return      the String projectID     
 */ 
    public String getProjectID() 
    {
        return projectID;
    }
    
/**
 * Returns the boolean sprintBacklog of the task. 
 * The Getter for the sprintBacklog attribute 
 *
 * @return      the boolean sprintBacklog     
 */ 
    public boolean isSprintBacklog() 
    {
        return sprintBacklog;
    }
    
/**
 * Returns the boolean deleted of the task. 
 * The Getter for the deleted attribute 
 *
 * @return      the boolean deleted     
 */
    public boolean isDeleted() 
    {
        return deleted;
    }

/**
 * Sets the id of the task. 
 * The Setter for the id attribute 
 *
 * @param  id the String id of the task    
 */      
    public void setId(long id) {
        this.id = id;
    }
    
/**
 * Sets the name of the task. 
 * The Setter for the name attribute 
 *
 * @param  name the String name of the task    
 */ 
    public void setName(String name) {
        this.name = name;
    }

/**
 * Sets the topPos of the task. 
 * The Setter for the topPos attribute 
 *
 * @param  topPos the String topPos of the task    
 */ 
    public void setTopPos(String topPos) {
        this.topPos = topPos;
    }

/**
 * Sets the leftpos of the task. 
 * The Setter for the leftpos attribute 
 *
 * @param  leftpos the String leftpos of the task    
 */     
    public void setLeftPos(String leftPos) {
        this.leftPos = leftPos;
    }

/**
 * Sets the status of the task. 
 * The Setter for the status attribute 
 *
 * @param  status the String status of the task    
 */     
    public void setStatus(String status) {
        this.status = status;
    }

/**
 * Sets the description of the task. 
 * The Setter for the description attribute 
 *
 * @param  description the String description of the task    
 */     
    public void setDescription(String description) {
        this.description = description;
    }

/**
 * Sets the person responsible for the task. 
 * The Setter for the responsible attribute 
 *
 * @param  responible the String person responsible for the task    
 */     
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

/**
 * Sets the points assigned to the task. 
 * The Setter for the points attribute 
 *
 * @param  points the String points of the task    
 */     
    public void setPoints(String points) {
        this.points = points;
    }

/**
 * Sets the days of the task. 
 * The Setter for the days attribute 
 *
 * @param  days the String days of the task    
 */ 
    public void setDays(String days) {
        this.days = days;
    }

/**
 * Sets the colour of the task. 
 * The Setter for the colour attribute 
 *
 * @param  colour the String colour of the task    
 */     
    public void setColour(String colour) {
        this.colour = colour;
    }

/**
 * Sets the sprintBacklog flag of the task. 
 * The Setter for the sprintBacklog attribute 
 *
 * @param  sprintBacklog the boolean sprintBacklog of the task    
 */     
    public void setSprintBacklog(boolean sprintBacklog) {
        this.sprintBacklog = sprintBacklog;
    }

/**
 * Sets the projectId of the task. 
 * The Setter for the projectId attribute 
 *
 * @param  projectId the String projectId of the task    
 */     
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

/**
 * Sets the comments of the task. 
 * The Setter for the comments attribute 
 *
 * @param  comments the String comments of the task    
 */     
    public void setComments(String comments) {
        this.comments = comments;
    }

/**
 * Sets the subTasks of the task. 
 * The Setter for the subTasks attribute 
 *
 * @param  subTasks the String subTasks of the task    
 */     
    public void setSubTasks(String subTasks) {
        this.subTasks = subTasks;
    }
    

}
