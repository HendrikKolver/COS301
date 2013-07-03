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
    
}
