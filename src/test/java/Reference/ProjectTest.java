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
public class ProjectTest {
    
    public ProjectTest() {
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
     * Test of getPreviousBurndownCharts method, of class Project.
     */
    @Test
    public void testGetPreviousBurndownCharts() {
        try{
            System.out.println("getPreviousBurndownCharts");
            Project instance = new Project();
            ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>> ();
            tmp.add(new ArrayList<Integer>());
            instance.setPreviousBurndownCharts(tmp);
            ArrayList result = instance.getPreviousBurndownCharts();
        }catch(Exception e)
        {
            fail("testGetPreviousBurndownCharts exception thrown");
        }
    }


    /**
     * Test of getBurndownPoints method, of class Project.
     */
    @Test
    public void testGetBurndownPoints() {
        try{
            System.out.println("getPreviousBurndownCharts");
            Project instance = new Project();
            ArrayList<Integer> tmp = new ArrayList<Integer> ();
            tmp.add(0);
            instance.setBurndownPoints(tmp);
            ArrayList result = instance.getBurndownPoints();
        }catch(Exception e)
        {
            fail("testGetBurndownPoints exception thrown");
        }
    }

   
    /**
     * Test of getRowCount method, of class Project.
     */
    @Test
    public void testGetRowCount() {
        try{
            System.out.println("getRowCount");
            Project instance = new Project();
            instance.setRowCount(1);
            int expResult = 1;
            int result = instance.getRowCount();
            assertEquals(expResult, result);
        }catch(Exception e)
        {
            fail("GetRowCount(), test failed");
        }
        
    }



    /**
     * Test of isUpdate method, of class Project.
     */
    @Test
    public void testIsUpdate() {
        try{
            System.out.println("isUpdate");
            Project instance = new Project();
            boolean expResult = false;
            boolean result = instance.isUpdate();
            assertEquals(expResult, result);
        }catch(Exception e)
        {
            fail("IsUpdate(), test failed");
        }
    }

    /**
     * Test of dbUpdate method, of class Project.
     */
    @Test
    public void testDbUpdate() {
        try{
            System.out.println("dbUpdate");
            Project instance = new Project();
            instance.update = true;
            instance.dbUpdate();
            boolean expResult = false;
            boolean result = instance.isUpdate();
        }catch(Exception e)
        {
           fail("DbUpdate(), test failed");
        }
    }


    /**
     * Test of getUsers method, of class Project.
     */
    @Test
    public void testGetUsers() {
        try{
            System.out.println("getUsers");
            Project instance = new Project();
            instance.addUser("Jannie");
            Object[] result = instance.getUsers();
            if(!result[0].equals("Jannie"))
                fail("GetUsers(), test failed values not correct");
        }catch(Exception e)
        {
            fail("GetUsers(), test failed");
        }
    }

    /**
     * Test of getUsersNotOnProject method, of class Project.
     */
    @Test
    public void testGetUsersNotOnProject() {
        try{
            System.out.println("getUsersNotOnProject");
            Reference.usernames.add(new User("j","j","j","j"));
            Reference.usernames.add(new User("k","k","k","k"));
            Project instance = new Project();
            instance.addUser("j");
            
            String[] result = instance.getUsersNotOnProject();
            System.out.println(result[0]);
            for (int i = 0; i < result.length; i++) {
                if(result[i].equals("j"))
                    fail("GetUsersNotOnProject(), value incorrect"); 
            }
            
        }catch(Exception e)
        {
            fail("GetUsersNotOnProject(), exception");
        }
    }

    /**
     * Test of getProjectStatus method, of class Project.
     */
    @Test
    public void testGetProjectStatus() {
        try{
            System.out.println("getProjectStatus");
            Project instance = new Project();
            instance.setId("112");
            Tasks t = new Tasks("1","1");
            t.setProjectID("112");
            t.setStatus("inProgress");
            Reference.tasks = new ArrayList<Tasks>();
            Reference.tasks.add(t);
            String expResult = "In Progress";
            
            String result = instance.getProjectStatus();
            System.out.println("EXP result: "+ expResult + "; "+ result);
            assertEquals(expResult, result);
        }catch(Exception e)
        {
             fail("GetProjectStatus(), exception");
        }
    }


    /**
     * Test of isCurrentBurndownChart method, of class Project.
     */
    @Test
    public void testIsCurrentBurndownChart() {
        try{
        System.out.println("isCurrentBurndownChart");
        Project instance = new Project();
        
        boolean result = instance.isCurrentBurndownChart();
        
        }catch(Exception e)
        {
            fail("IsCurrentBurndownChart(), exception");
        }
    }

}
