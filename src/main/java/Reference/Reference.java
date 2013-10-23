package Reference;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Richard
 */
public class Reference {
    public static WebSockets w;
    public static PlanningPoker p;
    public static ArrayList<Tasks> tasks; 
    public static ChatSocket chatSocket;
    public static ArrayList<Project> projects = new ArrayList<Project>();
    public static ArrayList<User> usernames = new ArrayList<User>();
    public static ArrayList<String> audit = new ArrayList<String>();
    
    
    
    public static ArrayList<User> getUsernames() {
        //This will be replaced by a load from the db. Only for testing
//        if(usernames.isEmpty())
//        {
//            User tmp = new User("admin","admin","admin","admin");
//            usernames.add(tmp);
//            tmp = new User("user","user","user","user");
//            usernames.add(tmp);
//            tmp = new User("user2","user2","user2","user2"); 
//        }
        return usernames;
    }

    public static void setUsernames(ArrayList<User> usernames) {
        Reference.usernames = usernames;
    }
    
   public static WebSockets getWebSockets()
   {
       return w;
   }
   
   public static PlanningPoker getPlanningPoker()
   {
       return p;
   }
   
   public static ArrayList<Tasks> getTasks()
   {
       return tasks;
   }
   
   public static ArrayList<Project> getProjects()
   {
       return projects;
   }  
   
   
}
