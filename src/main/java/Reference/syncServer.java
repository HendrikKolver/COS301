package Reference;

import Reference.WebSockets;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Hendrik
 */
@Singleton
@ManagedBean(name = "syncServer")
public class syncServer {
    //Function to start the sync server if it is not running
    public void startServer() {
            WebSockets.main();
    }
    
    public void startPlanningPoker()
    {
       PlanningPoker.main(); 
    }
    
    public void startChat()
    {
       ChatSocket.main(); 
    }
    
    public void getProjects()
    {
        if(Reference.projects.size()== 0)
        {
            Project tmp = new Project();
            tmp.id="1";
            tmp.usernames.add("Jannie");
            tmp.setProjectName("Project 1");
            Reference.projects.add(tmp);

            tmp = new Project();
            tmp.id="2";
            tmp.usernames.add("Jannie");
            tmp.setProjectName("Project 2");
            Reference.projects.add(tmp);
        }  
    }
    
}
