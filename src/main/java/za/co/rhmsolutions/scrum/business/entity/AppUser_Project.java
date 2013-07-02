/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.rhmsolutions.scrum.business.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Richard
 */
@Entity
public class AppUser_Project 
{
    @Id
    @ManyToOne (cascade=CascadeType.PERSIST) AppUser appUser;
    //@Id
    //@ManyToOne (cascade=CascadeType.PERSIST) Project project;
}
