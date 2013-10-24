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

    public Audit() 
    {
        
    }

    public Audit(String auditLog) {
        this.auditLog = auditLog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(String auditLog) {
        this.auditLog = auditLog;
    }
}
