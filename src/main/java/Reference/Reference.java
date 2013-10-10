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
   
           
}
