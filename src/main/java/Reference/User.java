/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

/**
 *
 * @author hendrik
 */
public class User {
    private String username = "";
    private String password = "";
    private String name = "";
    private String surname = "";
    private boolean update = false;
    
    public User(String username, String password, String name, String surname)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
    
    public User()
    {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        update = true;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        update = true;
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        update = true;
        this.surname = surname;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        
        this.update = update;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        update = true;
        this.username = username;
    }
    
    public void update()
    {
        update = false;
    }
    
}
