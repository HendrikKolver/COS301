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
    WebSockets()
    {
        System.out.println("constructor");
        burndownPoints.add(0);
    }

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
        
        String message = "burndown,";
            for(int x=0; x<burndownPoints.size();x++)
            {
                if(x==burndownPoints.size()-1)
                {
                    message+= burndownPoints.get(x);
                }else
                    message+= burndownPoints.get(x)+";";
            }
            connection.send(message);
        
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
        if(pieces[0].equals("commentsUpdate"))
        {
            message = "commentsUpdate,"+pieces[1]+","+pieces[2];
        }
        else if(pieces[0].equals("tasksUpdate"))
        {
           message = "tasksUpdate,"+pieces[1]+","+pieces[2]; 
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
            int temp = burndownPoints.get(burndownPoints.size()-1);
            burndownPoints.add(temp);
        }
        if(pieces[0].equals("id"))
        {
            Random r = new Random();
            int ID = r.nextInt(5000);
            connection.send(String.valueOf("id,"+ID));
            System.out.println("ID: "+ ID);
        }else
        {

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
                            int tmp = 0;
                            for (int i = 0; i < tasks.size(); i++) {
                                if(!(tasks.get(i).getStatus().equals("completed")))
                                {
                                    tmp += Integer.parseInt(tasks.get(i).getDays());
                                }
                            }
                            burndownPoints.set((burndownPoints.size()-1),tmp);
                            
                            System.out.println(burndownPoints.toString());
                            
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
//                                int tmp = 0;
//                                if(pieces[1] != null && pieces[1].length()>0)
//                                {
//                                    for (int i = 0; i < tasks.size(); i++) {
//                                        if(!(tasks.get(i).getStatus().equals("completed")))
//                                        {
//                                            tmp += Integer.parseInt(tasks.get(i).getPoints());
//                                        }
//                                    }
//                                    burndownPoints.set((burndownPoints.size()-1),tmp);
//
//                                    System.out.println(burndownPoints.toString());
//                                }
                                
                            }else if(checkString.equals("ys"))
                            {
                                tasks.get(x).setDays(pieces[1]);
                                int tmp = 0;
                                if(pieces[1] != null && pieces[1].length()>0)
                                {
                                    for (int i = 0; i < tasks.size(); i++) {
                                        if(!(tasks.get(i).getStatus().equals("completed")))
                                        {
                                            tmp += Integer.parseInt(tasks.get(i).getDays());
                                        }
                                    }
                                    burndownPoints.set((burndownPoints.size()-1),tmp);

                                    System.out.println(burndownPoints.toString());
                                }
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
            message = "burndown,";
            for(int x=0; x<burndownPoints.size();x++)
            {
                if(x==burndownPoints.size()-1)
                {
                    message+= burndownPoints.get(x);
                }else
                    message+= burndownPoints.get(x)+";";
            }
            for(int x=0; x<clients.size();x++)
            {
               clients.get(x).send(message); 
            }
            System.out.println("Message sent to clients, Task size: " + tasks.size());
        }
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