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
    String chatHistory;
    
    
    
    public ChatSocket()
    {
        System.out.println("constructor");
        chatHistory = "";
        
    }
	
    //initial connection made by client
    @Override
     public void onOpen(WebSocketConnection connection) {
        System.out.println("ClientConnected!");
        clients.add(connection);
        connection.send(chatHistory);
        connectionCount++;
        //System.out.println("clientCOunt: "+ connectionCount);
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
       chatHistory+= message;
        System.out.println("recievedMessage Chat");

        //send message to all clients
        for(int x=0; x<clients.size();x++)
        {
            if(!clients.get(x).equals(connection))
            {
                clients.get(x).send(message);
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
