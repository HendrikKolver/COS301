/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import java.util.ArrayList;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;
import org.webbitserver.handler.StaticFileHandler;

/**
 *
 * @author Hendrik
 */
public class PlanningPoker  extends BaseWebSocketHandler {

    private int connectionCount;
	private static PlanningPoker w ;
    
    ArrayList<WebSocketConnection> clients = new ArrayList<WebSocketConnection>();
    ArrayList<Tasks> tasks;
    ArrayList<String> currentState;
    ArrayList<String> choices = new ArrayList<String>();
    private int count;
    
    
    public PlanningPoker()
    {
        System.out.println("constructor");
        tasks = Reference.getTasks();
        count = 0;
        currentState = new ArrayList<String>();
    }
	
    //initial connection made by client
    @Override
     public void onOpen(WebSocketConnection connection) {
        boolean exists = false;
        System.out.println("connectionData:"+connection.httpRequest().body());
        
            System.out.println("ClientConnected to planning poker!");
            clients.add(connection);
            connectionCount++;
            //connection.send("hello");  
            
            

        System.out.println("clientCOunt: "+ connectionCount);
        
    }

    //client disconnects
    @Override
    public void onClose(WebSocketConnection connection) {
        clients.remove(connection);  
        connectionCount--;
    }

    //client sends a message
    @Override
    public void onMessage(WebSocketConnection connection, String message) {
        tasks = Reference.getTasks();
        System.out.println("recievedMessage");
        String pieces[] = message.split(",");
        
        if(pieces[0].equals("next"))
        {
           if(tasks == null || count >= tasks.size())
            {
                message ="done,done";
            }
            else
            {
                
                message = "taskInfo,"+tasks.get(count).getName()+","+tasks.get(count).getDescription();
                count++;
            }
           //send task to all clients
           for(int x=0; x<clients.size();x++)
            {
                    clients.get(x).send(message);
            }
        }else if(pieces[0].equals("choice"))
        {
            //do nothing with message
            choices.add(message);
            for(int x=0; x<clients.size();x++)
            {
                if(!clients.get(x).equals(connection))
                {
                    clients.get(x).send(message);
                }
            }
        }
        else if(pieces[0].equals("flip"))
        {
            //do nothing with message
            //currentState.add(message);
            for(int x=0; x<clients.size();x++)
            {
                if(!clients.get(x).equals(connection))
                {
                    clients.get(x).send(message);
                }
            }
        }
        else if(pieces[0].equals("join"))
        {
            for (int i = 0; i < choices.size(); i++) {
                connection.send(choices.get(i));   
            }
            
            for(int i=0; i<tasks.size();i++)
            {
                //This will eventually only be the project backlog that will be looped excluding tasks already in the sprintBacklog/Completed
                connection.send("unplannedTask,"+tasks.get(i).getName()+","+tasks.get(i).getID());
            }
                    
        }
        else if(pieces[0].equals("changeTask"))
        {
            for(int x=0; x<clients.size();x++)
            {
                if(!clients.get(x).equals(connection))
                {
                    clients.get(x).send(message);
                }
            }
            for(int x=0; x<clients.size();x++)
            {
                for(int i=0; i<tasks.size();i++)
                {
                    if(pieces[1].equals(tasks.get(i).getName()))
                        clients.get(x).send("description,"+tasks.get(i).getDescription());
                }
            }
        }
        
        
        
        

    }

    public static void main() {
        if(w == null)
        {
            w = new PlanningPoker();
            Reference.p=w;
            
        }

        WebServer webServer = WebServers.createWebServer(1236)
                .add("/planningPoker", w)
                .add(new StaticFileHandler("/web"));
        webServer.start();
        System.out.println("Client attemt to start Planning Poker");
    }
}
    
