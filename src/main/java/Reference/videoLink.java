package Reference;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import session.sessionBean;

@Singleton
@ManagedBean(name = "videoLink")
public class videoLink {
     @ManagedProperty(value = "#{sessionBean}")
    private sessionBean session;

    public sessionBean getSession() {
        return session;
    }

    public void setSession(sessionBean session) {
        this.session = session;
    }
    
    String address = "";
    boolean serverStarted = false;

    public boolean isServerStarted() {
        if(session.getProject()!=null)
            return session.getProject().serverStarted;
        else
            return true;
    }

    public void setServerStarted(boolean serverStarted) {
        if(session.getProject()!=null)
            session.getProject().serverStarted = serverStarted;
    }

    public String getAddress() {
        System.out.println("get Address!: " + videoAddressHolder.address);
        if(session.getProject()!=null)
            return session.getProject().videoUrl;
        else
            return "";
    }

    public void setAddress(String address) {
        System.out.println("address!: " + address);
        session.getProject().videoUrl = address;
    }
    
    public void demoFunct()
    {
        System.out.println("dummy call");
    }
    
   
    
}
