
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
        connectionCount--;
    }

    public void onMessage(WebSocketConnection connection, String message) {
        for(int x=0; x<clients.size();x++)
            clients.get(x).send(message.toUpperCase());
        
        //System.out.println("Message:" + message);
        //connection.send(message.toUpperCase()); // echo back message in upper case
    }

    public static void main(String[] args) {
        WebServer webServer = WebServers.createWebServer(8080)
                .add("/hellowebsocket", new HelloWebSockets())
                .add(new StaticFileHandler("/web"));
        webServer.start();
        System.out.println("Server running at " + webServer.getUri());
    }
}