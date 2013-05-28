/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hendrik
 */
public class Tasks {
    private String name;
    private String ID;
    private String topPos,leftPos;
    private String status;
    private String message;
    private String tID;

    public String getLeftPos() {
        return leftPos;
    }

    public String getTopPos() {
        return topPos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public Tasks(String n, String i)
    {
        name = n;
        ID = i;
        message = "Your Text Here...";
        topPos = "0";
        leftPos = "0";
        tID = i+"ID";
    }
    
    public void setPos(String x, String y)
    {
        topPos = x; 
        leftPos = y;
    }
    
    public void setTextID(String s)
    {
        tID = s;
    }
    
    public String getTextID()
    {
        return tID;
    }
            
    
}
