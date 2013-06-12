
import java.util.ArrayList;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;
import org.webbitserver.handler.StaticFileHandler;

public class HelloWebSockets extends BaseWebSocketHandler {
    private int connectionCount;
    
    ArrayList<WebSocketConnection> clients = new ArrayList<WebSocketConnection>();
    ArrayList<Tasks> tasks = new ArrayList<Tasks>();

    public void onOpen(WebSocketConnection connection) {
        //connection.send("Hello! There are " + connectionCount + " other connections active");
        for(int x=0; x<tasks.size();x++)
        {
            String message = "position,"+tasks.get(x).getTopPos()+","+tasks.get(x).getLeftPos()+","+tasks.get(x).getID();
            connection.send(message);
            message = "text,"+tasks.get(x).getMessage()+","+tasks.get(x).getTextID();
            connection.send(message);
        }
        clients.add(connection);
        connectionCount++;
    }

    public void onClose(WebSocketConnection connection) {
        clients.remove(connection);  
        connectionCount--;
    }

    public void onMessage(WebSocketConnection connection, String message) {
        String pieces[] = message.split(",");
        boolean check = false;
        for(int x=0; x<tasks.size();x++)
        {
            
            if(pieces[0].equals("position"))
            {  
               
                if(pieces[3].equals(tasks.get(x).getID())) 
                {
                    check = true; 
                    break;
                }

            }
            else if(pieces[0].equals("text"))
            {
               
                if(pieces[3].equals(tasks.get(x).getID())) 
                {
                    check = true; 
                    break;
                }   
            }
        }
        System.out.println(check);
        if(!check)
        {
            
            Tasks tmp = new Tasks("default","default");
            if(pieces[0].equals("position"))
            {  
                
                tmp = new Tasks(pieces[3],pieces[3]); 
                tmp.setPos(pieces[1], pieces[2]);
            }
            else if(pieces[0].equals("text"))
            {
               
                tmp = new Tasks(pieces[3],pieces[3]);
                tmp.setTextID(pieces[2]);              
                tmp.setMessage(pieces[1]);
            }
            tasks.add(tmp);      
        }
        else
        {
           for(int x=0; x<tasks.size();x++)
           {
               if(pieces[0].equals("position"))
            {  
                
                if(pieces[3].equals(tasks.get(x).getID())) 
                {
                    tasks.get(x).setPos(pieces[1], pieces[2]);
                }

            }
            else if(pieces[0].equals("text"))
            {
               
                if(pieces[3].equals(tasks.get(x).getID())) 
                {
                    tasks.get(x).setMessage(pieces[1]); 
                }   
            }
           }
               
        }

            
        
        double bytesSent = 0;
        int messageLength = message.getBytes().length;
        for(int x=0; x<clients.size();x++)
        {
            if(!clients.get(x).equals(connection))
            {
                bytesSent += messageLength;
                clients.get(x).send(message);
            }
        }
        System.out.println("sent: "+ bytesSent +" bytes...");
    }

    public static void main() {
        WebServer webServer = WebServers.createWebServer(1234)
                .add("/websocket", new HelloWebSockets())
                .add(new StaticFileHandler("/web"));
        webServer.start();
        System.out.println("Server running at " + webServer.getUri());
    }
}