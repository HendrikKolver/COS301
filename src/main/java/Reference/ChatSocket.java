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
public class ChatSocket  extends BaseWebSocketHandler {

    private int connectionCount;
	private static ChatSocket w ;
    
    ArrayList<WebSocketConnection> clients = new ArrayList<WebSocketConnection>();
    ArrayList<String> projectIdReference = new ArrayList<String>();
    
    
    
    
    
    public ChatSocket()
    {
        System.out.println("constructor");
        
        
    }
	
    //initial connection made by client
    @Override
     public void onOpen(WebSocketConnection connection) {
        System.out.println("ClientConnected!");
        clients.add(connection);
        projectIdReference.add("");
        connectionCount++;
    }

    //client disconnects
    @Override
    public void onClose(WebSocketConnection connection) {
        
        projectIdReference.remove(clients.indexOf(connection));
        clients.remove(connection);  
        connectionCount--;
    }

    //client sends a message
    @Override
    public void onMessage(WebSocketConnection connection, String message) {
        String pieces[] = message.split(",");
        System.out.println("recievedMessage Chat");

        //send message to all client
        if(pieces[0].equals("message"))
        {
            System.out.println("master id: "+ pieces[pieces.length-1]);
            for(int x=0; x<clients.size();x++)
            {
                System.out.println("message id: "+ projectIdReference.get(x));
                if(!clients.get(x).equals(connection) && projectIdReference.get(x).equals(pieces[pieces.length-1]))
                {
                    clients.get(x).send(pieces[1]);
                }
            }
        }else if(pieces[0].equals("join"))
        {
            for (int i = 0; i < clients.size(); i++) {
                if(connection.equals(clients.get(i)))
                {
                    projectIdReference.set(i, pieces[pieces.length-1]);
                    System.out.println("set value: "+ projectIdReference.get(i));        
                }
            }
        }
        
        
    }

    public static void main() {
        if(w == null)
        {
            w = new ChatSocket();
            Reference.chatSocket=w;
            
        }

        WebServer webServer = WebServers.createWebServer(1237)
                .add("/chatSocket", w)
                .add(new StaticFileHandler("/web"));
        webServer.start();
        System.out.println("Client attemt to start Chat");
    }
}
