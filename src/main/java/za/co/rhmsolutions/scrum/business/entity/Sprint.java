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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Richard
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
    
    public Sprint ()
    {
        
    }

    public Sprint(Long projectID, ArrayList<Integer> burndownChart) 
    {
        this.projectID = projectID;
        this.burndownChart = burndownChart;
        this.active = true;
    }
    
    public Sprint(Long projectID) 
    {
        this.projectID = projectID;
        this.burndownChart = new ArrayList<Integer>();
        this.burndownChart.add(0);
        this.active = true;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public ArrayList<Integer> getBurndownChart() {
        return burndownChart;
    }

    public void setBurndownChart(ArrayList<Integer> burndownChart) {
        this.burndownChart = burndownChart;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
