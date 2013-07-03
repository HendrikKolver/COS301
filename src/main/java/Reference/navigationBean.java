/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author growthpoint
 */
@ManagedBean(name = "navBean")
public class navigationBean {
    boolean scrumPage = false;
    String pageToLoad = "scrum.xhtml";

    public String getPageToLoad() {
        return pageToLoad;
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
