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
        for (int i = 0; i < clients.size(); i++) {
            if(connection.equals(clients.get(i)))
            {
               exists = true;
               break;
            }
        }
        
        if(!exists)
        {
            System.out.println("ClientConnected!");
            clients.add(connection);
            connectionCount++;
            //connection.send("hello");   
        }
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
           for(int x=0; x<clients.size();x++)
            {
               // if(!clients.get(x).equals(connection))
               // {
                    clients.get(x).send(message);
                //}
            }
        }else if(pieces[0].equals("choice"))
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
    
