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
    
    public int rowCount =0;
    public boolean serverStarted;

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    
    public Object[] getBurndowncharts()
    {
        ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
        
        for (int i = PreviousBurndownCharts.size()-1; i >= 0; i--)
        {
            temp.add(PreviousBurndownCharts.get(i));
        }
        return temp.toArray();
    }
    
    public Object[] getRecentBurndowncharts()
    {
        return burndownPoints.toArray();
    }
    
    public boolean isCurrentBurndownChart()
    {
        if (burndownPoints.toArray().length == 1)
        {
                return (burndownPoints.get(0)!=0);
        }
        else 
            return true;
    }
    
    public int getSprintSize()
    {
        return PreviousBurndownCharts.size();
    }
    
    
}
