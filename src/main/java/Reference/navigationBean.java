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
 * @author growthpoint
 */
@ManagedBean(name = "navBean")
public class navigationBean {
    @ManagedProperty(value = "#{sessionBean}")
    private sessionBean session;
     

    public sessionBean getSession() {
        return session;
    }
    
    public void setSession(sessionBean session) {
        this.session = session;
    }
    
    boolean scrumPage = true;
    String pageToLoad = "projects.xhtml";

    public String getPageToLoad() {
        try{
            if(session.isLoggedIn())
            {
                if(pageToLoad.equals("admin.xhtml"))
                {
                    if(session.isAdmin())
                        return pageToLoad;
                    else
                        return "";
                }else
                    return pageToLoad;
            }   
        }catch(Exception e)
        {
            System.out.println("NavigationBean.java, getPageToLoad()");
        }
        return "";
    }

    public void setPageToLoad(String pageToLoad) {
        this.pageToLoad = pageToLoad;
    }

    public boolean isScrumPage() {
        return scrumPage;
    }
    
    public void changeScrum(String page)
    {
        setPageToLoad(page);
        scrumPage = true;
    }
    
    
}
