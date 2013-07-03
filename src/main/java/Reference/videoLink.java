/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author growthpoint
 */
@Singleton
@ManagedBean(name = "videoLink")
public class videoLink {
    String address = "";

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
        System.out.println("wck;;;ovmvd");
    }
    
}
