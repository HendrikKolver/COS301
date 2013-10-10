/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author hendrik
 */
public class TasksTest {
    ArrayList<Tasks> taskTestList;
    
    public TasksTest() {
      taskTestList = new ArrayList<Tasks>(); 
      for(int x=0; x<5;x++)
          taskTestList.add(new Tasks(String.valueOf(x),String.valueOf(x)));
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of setComments method, of class Tasks.
     */
    @Test
    public void testSetComments() {
        System.out.println("setComments");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setComments("Comment"+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getComments().equals("Comment"+i))
                {
                   fail("value set incorrectly"); 
                }
                


            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }    
    }

    /**
     * Test of setSubTasks method, of class Tasks.
     */
    @Test
    public void testSetSubTasks() {
        System.out.println("setSubTasks");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setSubTasks("SubTask"+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getSubTasks().equals("SubTask"+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        } 
    }

   

    /**
     * Test of setColour method, of class Tasks.
     */
    @Test
    public void testSetColour() {
        System.out.println("setColour");
        
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setColour("Colour"+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getColour().equals("Colour"+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

    /**
     * Test of getColour method, of class Tasks.
     */
   

    /**
     * Test of setDays method, of class Tasks.
     */
    @Test
    public void testSetDays() {
        System.out.println("setDays");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setDays(""+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getDays().equals(""+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

    

    /**
     * Test of setPoints method, of class Tasks.
     */
    @Test
    public void testSetPoints() {
        System.out.println("setPoints");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setPoints(""+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getPoints().equals(""+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

    
    

    /**
     * Test of setResponsible method, of class Tasks.
     */
    @Test
    public void testSetResponsible() {
        System.out.println("setResponsible");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setResponsible("responsible"+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getResponsible().equals("responsible"+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

    
    /**
     * Test of setSprintBacklog method, of class Tasks.
     */
    @Test
    public void testSetSprintBacklog() {
        System.out.println("setSprintBacklog");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setSprintBacklog(true);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getSprintBacklog() == true)
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

   

    

/**
     * Test of setDescription method, of class Tasks.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setDescription("description"+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getDescription().equals("description"+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }
   

    /**
     * Test of setID method, of class Tasks.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setID(""+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getID().equals(""+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

   
    /**
     * Test of setName method, of class Tasks.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setName("name"+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getName().equals("name"+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }


    /**
     * Test of setStatus method, of class Tasks.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setStatus("status"+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getStatus().equals("status"+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

    

    /**
     * Test of setPos method, of class Tasks.
     */
    @Test
    public void testSetPos() {
        System.out.println("setPos");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setPos(""+i+1,""+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getTopPos().equals(""+i+1) || !taskTestList.get(i).getLeftPos().equals(""+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

    /**
     * Test of setTextID method, of class Tasks.
     */
    @Test
    public void testSetTextID() {
        System.out.println("setTextID");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).setTextID("id"+i);
                else
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getTextID().equals("id"+i))
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }



    /**
     * Test of dbUpdate method, of class Tasks.
     */
    @Test
    public void testDbUpdate() {
        System.out.println("dbUpdate");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)!= null)
                    taskTestList.get(i).dbUpdate();
                else
                {
                    fail("Object Null");
                    break;
                }
                
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }

    /**
     * Test of getUpdate method, of class Tasks.
     */
    @Test
    public void testGetUpdate() {
        System.out.println("getUpdate");
        try
        {
            for (int i = 0; i < taskTestList.size(); i++) {

                
                if(taskTestList.get(i)== null)
                {
                    fail("Object Null");
                    break;
                }
                
                if(!taskTestList.get(i).getUpdate() != false)
                {
                   fail("value set incorrectly"); 
                }
            }
        }catch(Exception e)
        {
            fail("Exception thrown"); 
        }
    }
}
