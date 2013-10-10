
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

    /*
     * Default Constructer needed for jpa
     */
    public AppUser() 
    {
        
    }

    /*
     * AppUser Constructer 
     * @params 
     * name: String
     * surname: String
     * Username: String
     * Password: String (MD5 Hashed)
     */
    public AppUser(String name, String surname, String Username, String Password) {
        this.name = name;
        this.surname = surname;
        this.Username = Username;
        this.Password = Password;
    }

    /*
     * Returns Password as String
     */
    public String getPassword() {
        return Password;
    }

    /*
     *Sets Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /*
     * Returns user's name as String
     */
    public String getName() {
        return name;
    }

    /*
     * Sets name as String
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
