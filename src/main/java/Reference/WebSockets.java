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

    public ArrayList<Tasks> getTasks()
    {
        return tasks;
    }
        
    public void addTask(String id)
    {
        Tasks tmp = new Tasks(id,id);
        tasks.add(tmp);
    }
    
    public void sendTasks()
    {
        for(int x=0; x<clients.size();x++)
        {
            int i = tasks.size()-1;
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
    
    public void sendAllTasks()
    {
        for(int x=0; x<clients.size();x++)
        {
            for(int i=0; i<tasks.size();i++)
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
	
    //initial connection made by client
    @Override
    public void onOpen(WebSocketConnection connection) {
        for(int x=0; x<tasks.size();x++)
        {
            String message = "position,"+tasks.get(x).getTopPos()+","+tasks.get(x).getLeftPos()+","+tasks.get(x).getID();
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
        }
        connection.send("addRow,"+rowCount);
        clients.add(connection);
        connectionCount++;
        System.out.println("Task size:" + tasks.size());
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
        String pieces[] = message.split(",");
        boolean check = false;

        if(pieces[0].equals("add"))
        {
            Tasks tmp = new Tasks(pieces[1],pieces[1]);
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
                        }
                        break;
                    }   
                }

            }
        }
        else if (pieces[0].equals("addRow"))
        {
            ++rowCount;
            System.out.println("rowCount: "+rowCount);
            message = "addRow,"+rowCount;
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