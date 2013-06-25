package Reference;

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
    private boolean update;
    
    public Tasks(String n, String i)
    {
        update = true;
        name = n;
        ID = i;
        message = "Your Text Here...";
        topPos = "0";
        leftPos = "0";
        tID = i+"ID";
    }

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
        update = true;
        this.message = message;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        update = true;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        update = true;
        this.name = name;
    }

    public String getStatus() {
        return status;
    }
    
    public void setPos(String x, String y)
    {
        topPos = x; 
        leftPos = y;
        update = true;
    }
    
    public void setTextID(String s)
    {
        update = true;
        tID = s;
        
    }
    
    public String getTextID()
    {
        return tID;
        
    }
    
    public void dbUpdate()
    {
        update = false;
    }
            
    
}
