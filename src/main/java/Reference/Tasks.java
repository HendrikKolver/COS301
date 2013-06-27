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
    private String description;
    private String responsible;
    private String points;
    private String days;
    private String colour;
    
    public String getColour()
    {
        return colour;
    }
    
    public void setColour(String colour) {
        update = true;
        this.colour = colour;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        update = true;
        this.days = days;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        update = true;
        this.points = points;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        update = true;
        this.responsible = responsible;
    }
    private String tID;
    private boolean update;
    
    public Tasks(String n, String i)
    {
        update = true;
        name = "Task Name";
        responsible = "Person Responsible";
        ID = i;
        description = "Task Description";
        points = "0";
        days = "0";
        topPos = "0";
        leftPos = "0";
        tID = i+"ID";
        colour = "yellow";
        status = "notStarted";
    }

    public String getLeftPos() {
        return leftPos;
    }

    public String getTopPos() {
        return topPos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String message) {
        update = true;
        this.description = message;
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
    
    public void setStatus(String status) {
        update = true;
        this.status = status;
    }
    
    public void setPos(String x, String y)
    {
        topPos = x; 
        leftPos = y;
        update = true;
        if (Double.parseDouble(y)<273)
            setStatus("notStarted");
        else if (Double.parseDouble(y)<722)
            setStatus("inProgress");
        else 
            setStatus("completed");
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
    
    public boolean getUpdate()
    {
        return update; 
    }
            
    
}
