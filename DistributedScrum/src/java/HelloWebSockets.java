
import java.util.ArrayList;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.WebSocketConnection;
import org.webbitserver.handler.StaticFileHandler;

public class HelloWebSockets extends BaseWebSocketHandler {
    private int connectionCount;
    ArrayList<WebSocketConnection> clients = new ArrayList<WebSocketConnection>();

    public void onOpen(WebSocketConnection connection) {
        connection.send("Hello! There are " + connectionCount + " other connections active");
        clients.add(connection);
        connectionCount++;
    }

    public void onClose(WebSocketConnection connection) {
        clients.remove(connection);  
        connectionCount--;
    }

    public void onMessage(WebSocketConnection connection, String message) {
        
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