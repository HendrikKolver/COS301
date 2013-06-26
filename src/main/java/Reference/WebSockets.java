package Reference;


import java.util.ArrayList;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;
import org.webbitserver.handler.StaticFileHandler;

public class WebSockets extends BaseWebSocketHandler {
    private static WebSockets w ;

    
    ArrayList<WebSocketConnection> clients = new ArrayList<WebSocketConnection>();
    ArrayList<Tasks> tasks = new ArrayList<Tasks>();

    public ArrayList<Tasks> getTasks()
    {
        return tasks;
    }
    //initial connection made by client
    @Override
    public void onOpen(WebSocketConnection connection) {
        for(int x=0; x<tasks.size();x++)
        {
            String message = "position,"+tasks.get(x).getTopPos()+","+tasks.get(x).getLeftPos()+","+tasks.get(x).getID();
            connection.send(message);
            message = "text,"+tasks.get(x).getMessage()+","+tasks.get(x).getTextID();
            connection.send(message);
        }
        clients.add(connection);
        
        System.out.println("Task size:" + tasks.size());
    }

    //client disconnects
    @Override
    public void onClose(WebSocketConnection connection) {
        clients.remove(connection);  
       
    }

    //client sends a message
    @Override
    public void onMessage(WebSocketConnection connection, String message) {
        String pieces[] = message.split(",");
        boolean check = false;
        
        if(pieces[0].equals("add"))
        {
            Tasks tmp = new Tasks(pieces[1],pieces[1]);
            tasks.add(tmp);
            
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
                    tasks.get(x).dbUpdate();
                    break;
                }

            }
            else if(pieces[0].equals("text"))
            {
               
                if(pieces[3].equals(tasks.get(x).getID())) 
                {
                    tasks.get(x).setMessage(pieces[1]); 
                    tasks.get(x).dbUpdate();
                    break;
                }   
            }
          }
        }

        for(int x=0; x<clients.size();x++)
        {
            if(!clients.get(x).equals(connection))
            {
                clients.get(x).send(message);
            }
        }
        System.out.println("Message sent to clients, Task size: " + tasks.size());
        
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