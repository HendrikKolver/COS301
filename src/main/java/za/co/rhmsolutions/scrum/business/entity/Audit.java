/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.entity;

import javax.persistence.*;

/**
 *
 * @author Richard O'Brien
 */
@Entity
public class Audit
{
    @Id
    @GeneratedValue
    private Long id;
    
    @Lob
    @Column(name="auditLog", length=4096)
    String auditLog;

/**
 * The default constructor method of the Task Entity class needed for JPA.
 * 
 */
    public Audit() 
    {
        
    }
    
/**
 * The constructor method of the Audit class.
 * 
 * @param  name  The string auditLog of the Audit instance
 */
    public Audit(String auditLog) {
        this.auditLog = auditLog;
    }

/**
 * Returns id of an Audit instance. 
 * The Getter for the id attribute 
 *
 * @return      the id      
 */    
    public Long getId() {
        return id;
    }

/**
 * Sets the id of the Audit instance. 
 * The Setter for the id attribute 
 *
 * @param  id the id of the Audit instance    
 */     
    public void setId(Long id) {
        this.id = id;
    }

/**
 * Returns String auditLog of an Audit instance. 
 * The Getter for the auditLog attribute 
 *
 * @return      the id      
 */     
    public String getAuditLog() {
        return auditLog;
    }

/**
 * Sets the auditLog of the Audit instance. 
 * The Setter for the auditLog attribute 
 *
 * @param  auditLog the String auditLog of the Audit instance    
 */    
    public void setAuditLog(String auditLog) {
        this.auditLog = auditLog;
    }
}
