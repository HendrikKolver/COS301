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
            tmp.usernames.add("admin");
            tmp.usernames.add("Richard");
            tmp.setProjectName("Project 1");
            tmp.videoUrl = "#meeting-roomid-fls8c0ud-jh";
            Reference.projects.add(tmp);

            tmp = new Project();
            tmp.id="2";
            tmp.usernames.add("admin");
            tmp.setProjectName("Project 2");
            tmp.videoUrl = "#meeting-roomid-g0y49otb-exko6r";
            Reference.projects.add(tmp);
        }
        
    }

    
}
