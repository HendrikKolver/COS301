/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import java.util.ArrayList;

/**
 *
 * @author hendrik
 */
public class Project {
    String id;
    ArrayList<Tasks> tasks;
    String videoUrl;
    ArrayList<String> usernames;
    public ArrayList<ArrayList<Integer>> PreviousBurndownCharts = new ArrayList<ArrayList<Integer>> ();
    public ArrayList<Integer> burndownPoints = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> getPreviousBurndownCharts() {
        return PreviousBurndownCharts;
    }

    public void setPreviousBurndownCharts(ArrayList<ArrayList<Integer>> PreviousBurndownCharts) {
        this.PreviousBurndownCharts = PreviousBurndownCharts;
    }

    public ArrayList<Integer> getBurndownPoints() {
        return burndownPoints;
    }

    public void setBurndownPoints(ArrayList<Integer> burndownPoints) {
        this.burndownPoints = burndownPoints;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    
    public int rowCount =0;
    public boolean serverStarted;
    
    public boolean update = false;

    public boolean isUpdate() {
        return update;
    }
    
    public void dbUpdate()
    {
        update = false;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    String projectName;
    String projectNameDisplay;
    

    public Object[] getUsers()
    {
        return usernames.toArray();
    }

    public Project()
    {
        projectNameDisplay  = "none";
        serverStarted = false;
        burndownPoints.add(0);
        id="";
        videoUrl="";
        projectName="";
        tasks = new ArrayList<Tasks>();
        usernames = new ArrayList<String>();
    }
    
    public String[] getUsersNotOnProject()
    {
        try{
            int tmpCounter = 0;
            int size = 0;
            for (int i = 0; i < Reference.getUsernames().size(); i++) {
                boolean valid = true;
                for (int j = 0; j < usernames.size(); j++) {
                    if(Reference.getUsernames().get(i).getUsername().equals(usernames.get(j)))
                    {
                        valid = false;

                    }       
                }
                if(valid)
                    size++;
            }
            String[]  tmpList= new String[size];
            for (int i = 0; i < Reference.getUsernames().size(); i++) {
                boolean valid = true;
                for (int j = 0; j < usernames.size(); j++) {
                    if(Reference.getUsernames().get(i).getUsername().equals(usernames.get(j)))
                    {

                        valid = false;  
                    }  
                }
                if(valid)
                {
                    tmpList[tmpCounter] = Reference.getUsernames().get(i).getUsername();
                    tmpCounter++;
                }

            }
            return tmpList;
            
        }catch(Exception e)
        {
            System.out.println("Project.java, getUsersNotOnProject()");
        }
        return null;
        
    }
    
    public String getProjectStatus()
    {
        try{
            for(int i=0; i< Reference.getTasks().size();i++)
            {
                if(Reference.getTasks().get(i).getStatus().equals("inProgress") && Reference.getTasks().get(i).getProjectID().equals(this.id))
                {
                return "In Progress";  
                }
            }

            for(int i=0; i< Reference.getTasks().size();i++)
            {
                if(!Reference.getTasks().get(i).getStatus().equals("Completed")  && Reference.getTasks().get(i).getProjectID().equals(this.id))
                {
                return "Not Started";  
                }
            }
        }catch(Exception e)
        {
            System.out.println("Project.java, getProjectStatus()");
        }
        
        
        return "Completed";
    }
    
    public void addTask(Tasks t)
    {
        tasks.add(t);
    }
    
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        projectNameDisplay = projectName;
        this.projectName = projectName;
    }

    public String getProjectNameDisplay() {
        return projectNameDisplay;
    }

    public void setProjectNameDisplay(String projectNameDisplay) {
        this.projectNameDisplay = projectNameDisplay;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object[] getTasks() {
        return tasks.toArray();
    }

    public void setTasks(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }
    
    public void addUser(String name)
    {
        update = true;
        usernames.add(name);
    }
    
    public void removeUser(String name)
    {
         update = true;
        usernames.remove(name);
    }
    

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    
    public Object[] getBurndowncharts()
    {
        try{
            ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();

            for (int i = PreviousBurndownCharts.size()-1; i >= 0; i--)
            {
                temp.add(PreviousBurndownCharts.get(i));
            }
            return temp.toArray();
        }catch(Exception e)
        {
            System.out.println("Project.java, getBurndowncharts()");
        }
        return null;
    }
    
    public Object[] getRecentBurndowncharts()
    {
        return burndownPoints.toArray();
    }
    
    //check if current burndownchart exists
    public boolean isCurrentBurndownChart()
    {
        try
        {
            if (burndownPoints.toArray().length == 1)
            {
                    return (burndownPoints.get(0)!=0);
            }
        }catch(Exception e)
        {
            System.out.println("Project.java, isCurrentBurndownChart()");
        }
            return true;
    }
    
    public int getSprintSize()
    {
        return PreviousBurndownCharts.size();
    }
    
    
}
