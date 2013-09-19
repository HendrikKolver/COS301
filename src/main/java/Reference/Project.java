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
    public ArrayList<Integer> burndownPoints = new ArrayList<Integer>();
    public int rowCount =0;

    String projectName;

    

    public Project()
    {
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
        this.projectName = projectName;
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
    
    
}
