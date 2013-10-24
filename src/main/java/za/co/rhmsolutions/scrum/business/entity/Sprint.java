/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.entity;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Richard O'Brien
 */

@Entity
public class Sprint
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long projectID;
    ArrayList<Integer> burndownChart;
    boolean active;

/**
 * The default constructor method of the Sprint Entity class needed for JPA.
 */    
    public Sprint ()
    {
        
    }

/**
 * The constructor method of the Sprint Entity class.
 * 
 * @param  projectID  The id of the associated Project
 * @param  burndownChart The ArrayList of integer values representing the burndownChart
 */
    public Sprint(Long projectID, ArrayList<Integer> burndownChart) 
    {
        this.projectID = projectID;
        this.burndownChart = burndownChart;
        this.active = true;
    }

/**
 * The constructor method of the Sprint Entity class.
 * 
 * @param  projectID  The id of the associated Project
 */    
    public Sprint(Long projectID) 
    {
        this.projectID = projectID;
        this.burndownChart = new ArrayList<Integer>();
        this.burndownChart.add(0);
        this.active = true;
    }

/**
 * Returns a Long projectID of the associated Project. 
 * The Getter for the projectID attribute 
 *
 * @return the projectID      
 */    
    public Long getProjectID() {
        return projectID;
    }

/**
 * Sets the projectID of the Project associated with the Sprint. 
 * The Setter for the projectID attribute 
 *
 * @param  projectID the projectID associated with the Sprint    
 */    
    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

/**
 * Returns the BurndownChart of the Sprint. 
 * The Getter for the burndownChart attribute 
 *
 * @return the burndownChart      
 */    
    public ArrayList<Integer> getBurndownChart() {
        return burndownChart;
    }

/**
 * Sets the BurndownChart of the Sprint. 
 * The Setter for the burndownChart attribute 
 *
 * @param  burndownChart the ArrayList of integers representing the burndownChart of the Sprint    
 */      
    public void setBurndownChart(ArrayList<Integer> burndownChart) {
        this.burndownChart = burndownChart;
    }

/**
 * Returns the boolean active flag of the Sprint. 
 * The Getter for the active attribute 
 *
 * @return the boolean active flag      
 */     
    public boolean isActive() {
        return active;
    }

/**
 * Sets the boolean active flag of the Sprint. 
 * The Setter for the projectID attribute 
 *
 * @param  active the boolean flag identifying if the sprint is active    
 */     
    public void setActive(boolean active) {
        this.active = active;
    } 

/**
 * Returns the id of the Sprint. 
 * The Getter for the id attribute 
 *
 * @return the id      
 */    
    public Long getId() {
        return id;
    }

/**
 * Sets the id of the Sprint. 
 * The Setter for the id attribute 
 *
 * @param  id the id of the sprint   
 */     
    public void setId(Long id) {
        this.id = id;
    }
}
