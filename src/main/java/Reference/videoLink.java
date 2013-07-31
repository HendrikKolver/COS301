package Reference;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;

@Singleton
@ManagedBean(name = "videoLink")
public class videoLink {
    String address = "";
    boolean serverStarted = false;

    public boolean isServerStarted() {
        return videoAddressHolder.serverStarted;
    }

    public void setServerStarted(boolean serverStarted) {
        videoAddressHolder.serverStarted = serverStarted;
    }

    public String getAddress() {
        System.out.println("get Address!: " + videoAddressHolder.address);
        return videoAddressHolder.address;
    }

    public void setAddress(String address) {
        System.out.println("address!: " + address);
        videoAddressHolder.address = address;
    }
    
    public void demoFunct()
    {
        System.out.println("dummy call");
    }
    
   
    
}
