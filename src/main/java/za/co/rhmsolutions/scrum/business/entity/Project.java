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
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    String name;
    String company;
    
    ArrayList<String> usernames;
    
    ArrayList<ArrayList<Integer>> PreviousBurndownCharts;
    ArrayList<Integer> burndownPoints;
    
    boolean deleted;

    //Needed for jpa
    public Project() 
    {
        
    }

    public Project(String name, String company) {
        this.name = name;
        this.company = company;
        
        usernames = new ArrayList<String>();
        PreviousBurndownCharts = new ArrayList<ArrayList<Integer>> ();
        burndownPoints = new ArrayList<Integer>();
        
        deleted = false;
    }
    
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public ArrayList<ArrayList<Integer>> getPreviousBurndownCharts() {
        return PreviousBurndownCharts;
    }

    public ArrayList<Integer> getBurndownPoints() {
        return burndownPoints;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public void setPreviousBurndownCharts(ArrayList<ArrayList<Integer>> PreviousBurndownCharts) {
        this.PreviousBurndownCharts = PreviousBurndownCharts;
    }

    public void setBurndownPoints(ArrayList<Integer> burndownPoints) {
        this.burndownPoints = burndownPoints;
    }

}
