/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import session.sessionBean;

/**
 *
 * @author hendrik
 */

@ManagedBean(name = "projectManagementBean")
public class ProjectManagement {
    @ManagedProperty(value = "#{sessionBean}")
    private sessionBean session;

    public sessionBean getSession() {
        return session;
    }
    
    public void doNothing()
    {
        System.out.println("doNothing");
    }

    public void setSession(sessionBean session) {
        this.session = session;
    }
    
    String id;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void changeSessionProject()
    {
        System.out.println("setting: " + id);
        for (int i = 0; i < Reference.projects.size(); i++) {
            {
                if(Reference.projects.get(i).id.equals(id))
                {
                    //set session variable
                    session.setProject(Reference.projects.get(i));
                    break;
                    
                }
            }
            
        }
    }
}
