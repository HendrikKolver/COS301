package Reference;

import java.util.ArrayList;
import java.util.Random;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;
import org.webbitserver.handler.StaticFileHandler;

public class WebSockets extends BaseWebSocketHandler {
    private int connectionCount;
    private int rowCount = 0;
	private static WebSockets w ;
    
    ArrayList<WebSocketConnection> clients = new ArrayList<WebSocketConnection>();
    ArrayList<Tasks> tasks = new ArrayList<Tasks>();
    ArrayList<Integer> burndownPoints = new ArrayList<Integer>();
    ArrayList<String> projectIDList = new ArrayList<String>();
    
    public WebSockets()
    {
        System.out.println("constructor");
        // get burndown points from DB
        
//        for (int i = 0; i < Reference.projects.size(); i++) 
//        {
//            Reference.projects.get(i).burndownPoints.add(0);   
//        }
        
        Reference.tasks = this.tasks;
    }

    public ArrayList<Tasks> getTasks()
    {
        return tasks;
    }
        
    public void addTask(String id)
    {
        Tasks tmp = new Tasks(id,id);
        tasks.add(tmp);
        Reference.tasks = this.tasks;
    }
    
    public void sendTasks()
    {
        for(int x=0; x<clients.size();x++)
        {
            int i = tasks.size()-1;
            if(tasks.get(i).getSprintBacklog()  && tasks.get(i).getProjectID().equals(projectIDList.get(x)))
            {
                String message = "position,"+tasks.get(i).getTopPos()+","+tasks.get(i).getLeftPos()+","+tasks.get(i).getID();
                clients.get(x).send(message);

                String ID = tasks.get(i).getID();
                message = "text,"+tasks.get(i).getName()+","+ID+"StickyTaskName";
                clients.get(x).send(message);
                message = "text,"+tasks.get(i).getResponsible()+","+ID+"StickyResponsible";
                clients.get(x).send(message);
                message = "text,"+tasks.get(i).getDescription()+","+ID+"StickyDescription";
                clients.get(x).send(message);
                message = "text,"+tasks.get(i).getPoints()+","+ID+"StickyPoints";
                clients.get(x).send(message);
                message = "text,"+tasks.get(i).getDays()+","+ID+"StickyDays";
                clients.get(x).send(message);
                message = "colour,"+tasks.get(i).getColour()+",a"+","+tasks.get(i).getID();
                clients.get(x).send(message);
            }
        }
    }
    
    public void sendAllTasks()
    {
        for(int x=0; x<clients.size();x++)
        {
            
            for(int i=0; i<tasks.size();i++)
            {
                if(tasks.get(i).getSprintBacklog() && tasks.get(i).getProjectID().equals(projectIDList.get(x)))
                {   
                    String message = "position,"+tasks.get(i).getTopPos()+","+tasks.get(i).getLeftPos()+","+tasks.get(i).getID();
                    clients.get(x).send(message);

                    String ID = tasks.get(i).getID();
                    message = "text,"+tasks.get(i).getName()+","+ID+"StickyTaskName";
                    clients.get(x).send(message);
                    message = "text,"+tasks.get(i).getResponsible()+","+ID+"StickyResponsible";
                    clients.get(x).send(message);
                    message = "text,"+tasks.get(i).getDescription()+","+ID+"StickyDescription";
                    clients.get(x).send(message);
                    message = "text,"+tasks.get(i).getPoints()+","+ID+"StickyPoints";
                    clients.get(x).send(message);
                    message = "text,"+tasks.get(i).getDays()+","+ID+"StickyDays";
                    clients.get(x).send(message);
                    message = "colour,"+tasks.get(i).getColour()+",a"+","+tasks.get(i).getID();
                    clients.get(x).send(message);
                }
            }
        }
    }
	
    //initial connection made by client
    @Override
     public void onOpen(WebSocketConnection connection) {
        
        clients.add(connection);
        projectIDList.add("");
        connectionCount++;
        System.out.println("Client scrum count:" + connectionCount);
    }

    //client disconnects
    @Override
    public void onClose(WebSocketConnection connection) {
        
        projectIDList.remove(clients.indexOf(connection));
        
        clients.remove(connection); 
        
        connectionCount--;
    }
    
    public String[] removeUnwantedTags(String list[])
    {
        for (int i = 0; i < list.length; i++) {
            list[i]= list[i].replace("(?i)script", "");
            list[i]= list[i].replace("(?i)script", "");
        }
        return list;
        
    }

    //client sends a message
    @Override
    public void onMessage(WebSocketConnection connection, String message) {
        
        String pieces[] = message.split(",");
        pieces = removeUnwantedTags(pieces);
        this.tasks= Reference.tasks; 
        boolean check = false;
        String tmpProjectID =pieces[pieces.length-1];
        if(pieces[0].equals("join"))
        {
            for (int i = 0; i < clients.size(); i++) {
                if(connection.equals(clients.get(i)))
                {
                    projectIDList.set(i, tmpProjectID);
                }
                
            }
           //-------------------------------------------------------------------------
            //TODO update everything below this point to cater for multiple projects
            //-------------------------------------------------------------------------
           for(int x=0; x<tasks.size();x++)
            {
                if(tasks.get(x).getSprintBacklog() && tasks.get(x).getProjectID().equals(tmpProjectID))
                {
                    message = "position,"+tasks.get(x).getTopPos()+","+tasks.get(x).getLeftPos()+","+tasks.get(x).getID();
                    connection.send(message);

                    String ID = tasks.get(x).getID();
                    message = "text,"+tasks.get(x).getName()+","+ID+"StickyTaskName";
                    connection.send(message);
                    message = "text,"+tasks.get(x).getResponsible()+","+ID+"StickyResponsible";
                    connection.send(message);
                    message = "text,"+tasks.get(x).getDescription()+","+ID+"StickyDescription";
                    connection.send(message);
                    message = "text,"+tasks.get(x).getPoints()+","+ID+"StickyPoints";
                    connection.send(message);
                    message = "text,"+tasks.get(x).getDays()+","+ID+"StickyDays";
                    connection.send(message);
                    message = "colour,"+tasks.get(x).getColour()+",a"+","+tasks.get(x).getID();
                    connection.send(message);
                    message = "commentsUpdate,"+tasks.get(x).getComments()+","+tasks.get(x).getID();
                    connection.send(message);
                    message = "tasksUpdate,"+tasks.get(x).getSubTasks()+","+tasks.get(x).getID();
                    connection.send(message);
                }
            }
            int tmp = 0;
           for (int i = 0; i < tasks.size(); i++) {
                if(!(tasks.get(i).getStatus().equals("completed")) && tasks.get(i).getSprintBacklog() && tasks.get(i).getProjectID().equals(tmpProjectID))
                {
                    tmp += Integer.parseInt(tasks.get(i).getDays());
                }
            }
           
            for (int i = 0; i < Reference.projects.size(); i++) {
                if(Reference.projects.get(i).getId().equals(tmpProjectID))
                {
                    Reference.projects.get(i).burndownPoints.set((Reference.projects.get(i).burndownPoints.size()-1),tmp);
                    message = "burndown,";
                    for(int x=0; x<Reference.projects.get(i).burndownPoints.size();x++)
                    {
                        if(x==Reference.projects.get(i).burndownPoints.size()-1)
                        {
                            message+= Reference.projects.get(i).burndownPoints.get(x);
                        }else
                            message+= Reference.projects.get(i).burndownPoints.get(x)+";";
                    }
                    connection.send(message);
                    
                    connection.send("addRow,"+Reference.projects.get(i).rowCount); 
                    break;
                }
                
            }
            
        
            
        }
        else
        {
            

            if(pieces[0].equals("commentsUpdate"))
            {
                message = "commentsUpdate,"+pieces[1]+","+pieces[2];
                for(int x=0; x<tasks.size();x++)
                    {
                        if(pieces[2].equals(tasks.get(x).getID())) 
                        {
                            tasks.get(x).setComments(pieces[1]); 
                            break;
                        }   
                    }
            }
            else if(pieces[0].equals("tasksUpdate"))
            {
            message = "tasksUpdate,"+pieces[1]+","+pieces[2];
            for(int x=0; x<tasks.size();x++)
                    {
                        if(pieces[2].equals(tasks.get(x).getID())) 
                        {
                            tasks.get(x).setSubTasks(pieces[1]); 
                            break;
                        }   
                    }
            }
            else if(pieces[0].equals("closeOptions"))
            {
                message = "closeOptions,"+pieces[1]; 
            }
            else if(pieces[0].equals("openOptions"))
            {
                message = "openOptions,"+pieces[1]; 
            }
            else if (pieces[0].equals("addDay"))
            {
                for (int i = 0; i < Reference.projects.size(); i++) 
                {
                    if(Reference.projects.get(i).getId().equals(tmpProjectID))
                    {
                        int temp = Reference.projects.get(i).burndownPoints.get(Reference.projects.get(i).burndownPoints.size()-1);
                        Reference.projects.get(i).burndownPoints.add(temp);
                    }
                }
                
            }
            if(pieces[0].equals("add"))
            {
                Tasks tmp = new Tasks(pieces[1],pieces[1]);
                tmp.setProjectID(tmpProjectID);
                tasks.add(tmp);
                System.out.println("TaskID: " + tmp.getID());

            }
            else if(pieces[0].equals("remove"))
            {
                for(int x=0; x<tasks.size();x++)
                {
                    System.out.println(pieces[1]);
                    System.out.println(tasks.get(x).getID());
                    if(pieces[1].equals(tasks.get(x).getID())) 
                    {
                        tasks.remove(x);                    
                    }   
                }
            }
            else if (pieces[0].equals("position") || pieces[0].equals("text"))
            {


                    for(int x=0; x<tasks.size();x++)
                    {
                        if(pieces[0].equals("position"))
                        {  
                            if(pieces[3].equals(tasks.get(x).getID())) 
                            {
                                System.out.println(pieces[1]);
                                tasks.get(x).setPos(pieces[1], pieces[2]);
                                int tmp = 0;
                                for (int i = 0; i < tasks.size(); i++) {
                                    if(!(tasks.get(i).getStatus().equals("completed")) && tasks.get(i).getSprintBacklog())
                                    {
                                        tmp += Integer.parseInt(tasks.get(i).getDays());
                                    }
                                }
                                for (int i = 0; i < Reference.projects.size(); i++) 
                                {
                                    if(Reference.projects.get(i).getId().equals(tmpProjectID))
                                    {
                                        Reference.projects.get(i).burndownPoints.set((Reference.projects.get(i).burndownPoints.size()-1),tmp);
                                    }
                                }

                                break;
                            }

                        }
                        else if(pieces[0].equals("text"))
                        {
                            
                        int length = pieces[2].length();
                        String checkString = pieces[2].substring(length-2,length);
                        // System.out.println("Check!!!!!!!!!!!!!!!!!!: "+ checkString);
                            if(pieces[3].equals(tasks.get(x).getID())) 
                            {
                                if(checkString.equals("me"))
                                {
                                    tasks.get(x).setName(pieces[1]); 
                                }else if(checkString.equals("le"))
                                {
                                    tasks.get(x).setResponsible(pieces[1]); 
                                }else if(checkString.equals("on"))
                                {
                                    tasks.get(x).setDescription(pieces[1]);
                                }else if(checkString.equals("ts"))
                                {
                                    tasks.get(x).setPoints(pieces[1]); 

                                }else if(checkString.equals("ys"))
                                {
                                    
                                    tasks.get(x).setDays(pieces[1]);
                                    int tmp = 0;
                                    if(pieces[1] != null && pieces[1].length()>0)
                                    {
                                        
                                        for (int i = 0; i < tasks.size(); i++) {
                                            if(!(tasks.get(i).getStatus().equals("completed")) && tasks.get(i).getSprintBacklog() && tasks.get(i).getProjectID().equals(tmpProjectID))
                                            {
                                                
                                                tmp += Integer.parseInt(tasks.get(i).getDays());
                                            }
                                        }
                                        for (int i = 0; i < Reference.projects.size(); i++) 
                                        {
                                            if(Reference.projects.get(i).getId().equals(tmpProjectID))
                                            {
                                               Reference.projects.get(i).burndownPoints.set((Reference.projects.get(i).burndownPoints.size()-1),tmp);
                                            }
                                        }
                                    }
                                }
                                break;
                            }   
                        }

                    }
            }
            else if (pieces[0].equals("addRow"))
            {
                for (int i = 0; i < Reference.projects.size(); i++) 
                {
                    if(Reference.projects.get(i).getId().equals(tmpProjectID))
                    {
                        ++Reference.projects.get(i).rowCount;
                        message = "addRow,"+Reference.projects.get(i).rowCount;
                    }
                }
                
                //System.out.println("rowCount: "+rowCount);
                
            }else if (pieces[0].equals("colour"))
            {
                System.out.println("Colour: "+pieces[1]);
                for (int i = 0; i < tasks.size(); i++) 
                {
                    if(pieces[3].equals(tasks.get(i).getID())) 
                    {
                        tasks.get(i).setColour(pieces[1]);
                    }  
                }
                message = "colour,"+pieces[1]+","+pieces[2]+","+pieces[3];
            }

            for(int x=0; x<clients.size();x++)
            {
                System.out.println("Allclients: " + message+ " ; the id's: " +projectIDList.get(x) + ","+tmpProjectID);
                if(!clients.get(x).equals(connection) && projectIDList.get(x).equals(tmpProjectID))
                {
                    System.out.println("SpecificClients: " + message);
                    clients.get(x).send(message);
                }
            }
            
            message = "burndown,";
            
            for (int i = 0; i < Reference.projects.size(); i++) 
            {
                if(Reference.projects.get(i).getId().equals(tmpProjectID))
                {
                    for(int x=0; x<Reference.projects.get(i).burndownPoints.size();x++)
                    {
                    
                        if(x==Reference.projects.get(i).burndownPoints.size()-1)
                        {
                            message+= Reference.projects.get(i).burndownPoints.get(x);
                        }else
                            message+= Reference.projects.get(i).burndownPoints.get(x)+";";
                    }
                }
                
            }
            
            for(int x=0; x<clients.size();x++)
            {
                if(projectIDList.get(x).equals(tmpProjectID))
                {
                    clients.get(x).send(message);
                }
            }
            
            System.out.println("Message sent to clients, Task size: " + tasks.size());
        }
        Reference.tasks = this.tasks;
    }

    public static void main() {
        if(w == null)
        {
            w = new WebSockets();
            Reference.w=w;
        }

        WebServer webServer = WebServers.createWebServer(1234)
                .add("/websocket", w)
                .add(new StaticFileHandler("/web"));
        webServer.start();
        System.out.println("Client attemt to start server");
    }
}