
/*
 * The AppUser entity class. A class for users that creates a user object 
 * with the necessary user details. Will be persisted to the database
 */
package za.co.rhmsolutions.scrum.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Richard O'Brien
 */
@Entity
public class AppUser
{
    @Id
    @GeneratedValue
    private Long id;
    
    String name;
    String surname;
    String Username;
    String Password;
    boolean deleted;

/**
 * The default constructor method of the Task Entity class needed for JPA.
 * 
 */
    public AppUser() 
    {
        
    }
  
/**
 * The constructor method of the AppUser Entity class.
 * 
 * @param  name  The string name of the AppUser
 * @param  surname The string surname of the AppUser
 * @param  surname The unique string Username of the AppUser
 * @param  Password The hashed string Password of the AppUser
 */
    public AppUser(String name, String surname, String Username, String Password) {
        this.name = name;
        this.surname = surname;
        this.Username = Username;
        this.Password = Password;
        
        deleted = false;
    }

/**
 * Returns a String Password of the AppUser. 
 * The Getter for the Password attribute 
 *
 * @return      the String Password      
 */
    public String getPassword() {
        return Password;
    }

/**
 * Sets the Password of the AppUser. 
 * The Setter for the Password attribute 
 *
 * @param  Password the hashed String Password of the AppUser.    
 */ 
    public void setPassword(String Password) {
        this.Password = Password;
    }

/**
 * Returns a String name of the AppUser. 
 * The Getter for the name attribute 
 *
 * @return      the String name      
 */
    public String getName() {
        return name;
    }
    
/**
 * Sets the name of the AppUser. 
 * The Setter for the name  attribute 
 *
 * @param  name the String name of the AppUser.    
 */ 
    public void setName(String name) {
        this.name = name;
    }

/**
 * Returns a String surname of the AppUser. 
 * The Getter for the surname attribute 
 *
 * @return      the String surname      
 */
    public String getSurname() {
        return surname;
    }

/**
 * Sets the surname of the AppUser. 
 * The Setter for the surname attribute 
 *
 * @param  surname the String name of the AppUser.    
 */ 
    public void setSurname(String surname) {
        this.surname = surname;
    }

/**
 * Returns a String Username of the AppUser. 
 * The Getter for the Password attribute 
 *
 * @return      the String Username      
 */
    public String getUsername() {
        return Username;
    }

/**
 * Sets the Username of the AppUser. 
 * The Setter for the username attribute 
 *
 * @param  Username the String Username of the AppUser.    
 */ 
    public void setUsername(String Username) {
        this.Username = Username;
    }

/**
 * Returns the id of the AppUser. 
 * The Getter for the id attribute 
 *
 * @return      the id      
 */
    public Long getId() {
        return id;
    }

/**
 * Sets the id of the AppUser. 
 * The Setter for the id attribute 
 *
 * @param  id the id of the AppUser.    
 */     
    public void setId(Long id) {
        this.id = id;
    }
 
/**
 * Returns a boolean deleted of the AppUser. 
 * The Getter for the deleted attribute 
 *
 * @return      the boolean deleted      
 */
    public boolean isDeleted() {
        return deleted;
    }

/**
 * Sets the deleted flag of the AppUser. 
 * The Setter for the deleted attribute 
 *
 * @param  deleted the deleted flag of the AppUser.    
 */      
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
