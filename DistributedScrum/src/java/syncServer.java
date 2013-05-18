/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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
    private boolean running = false;
    private String line = "red";

    public String getLine() {
        
        return startServer();
    }

    public void setLine(String line) {
        this.line = line;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String startServer() {
      
            HelloWebSockets.main();
        //HelloWebSockets.main(a);
        return "connected";
    }
}
