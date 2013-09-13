/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author hendrik
 */
public class projectBeanTest {
    WebSockets w;
    public projectBeanTest() {
        w.main();
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

//    /**
//     * Test of getTaskID method, of class projectBean.
//     */
//    @Test
//    public void testGetTaskID() {
//        System.out.println("getTaskID");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getTaskID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setTaskID method, of class projectBean.
//     */
//    @Test
//    public void testSetTaskID() {
//        System.out.println("setTaskID");
//        String taskID = "";
//        projectBean instance = new projectBean();
//        instance.setTaskID(taskID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getProjectID method, of class projectBean.
//     */
//    @Test
//    public void testGetProjectID() {
//        System.out.println("getProjectID");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getProjectID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setProjectID method, of class projectBean.
//     */
//    @Test
//    public void testSetProjectID() {
//        System.out.println("setProjectID");
//        String projectID = "";
//        projectBean instance = new projectBean();
//        instance.setProjectID(projectID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEditDescription method, of class projectBean.
//     */
//    @Test
//    public void testGetEditDescription() {
//        System.out.println("getEditDescription");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getEditDescription();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEditDescription method, of class projectBean.
//     */
//    @Test
//    public void testSetEditDescription() {
//        System.out.println("setEditDescription");
//        String editDescription = "";
//        projectBean instance = new projectBean();
//        instance.setEditDescription(editDescription);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEditPersonResponsible method, of class projectBean.
//     */
//    @Test
//    public void testGetEditPersonResponsible() {
//        System.out.println("getEditPersonResponsible");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getEditPersonResponsible();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEditPersonResponsible method, of class projectBean.
//     */
//    @Test
//    public void testSetEditPersonResponsible() {
//        System.out.println("setEditPersonResponsible");
//        String editPersonResponsible = "";
//        projectBean instance = new projectBean();
//        instance.setEditPersonResponsible(editPersonResponsible);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEditTaskName method, of class projectBean.
//     */
//    @Test
//    public void testGetEditTaskName() {
//        System.out.println("getEditTaskName");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getEditTaskName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEditTaskName method, of class projectBean.
//     */
//    @Test
//    public void testSetEditTaskName() {
//        System.out.println("setEditTaskName");
//        String editTaskName = "";
//        projectBean instance = new projectBean();
//        instance.setEditTaskName(editTaskName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDescription method, of class projectBean.
//     */
//    @Test
//    public void testGetDescription() {
//        System.out.println("getDescription");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getDescription();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDaysLeft method, of class projectBean.
//     */
//    @Test
//    public void testGetDaysLeft() {
//        System.out.println("getDaysLeft");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getDaysLeft();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDaysLeft method, of class projectBean.
//     */
//    @Test
//    public void testSetDaysLeft() {
//        System.out.println("setDaysLeft");
//        String daysLeft = "";
//        projectBean instance = new projectBean();
//        instance.setDaysLeft(daysLeft);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getStoryPoints method, of class projectBean.
//     */
//    @Test
//    public void testGetStoryPoints() {
//        System.out.println("getStoryPoints");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getStoryPoints();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStoryPoints method, of class projectBean.
//     */
//    @Test
//    public void testSetStoryPoints() {
//        System.out.println("setStoryPoints");
//        String storyPoints = "";
//        projectBean instance = new projectBean();
//        instance.setStoryPoints(storyPoints);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDescription method, of class projectBean.
//     */
//    @Test
//    public void testSetDescription() {
//        System.out.println("setDescription");
//        String description = "";
//        projectBean instance = new projectBean();
//        instance.setDescription(description);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPersonResponsible method, of class projectBean.
//     */
//    @Test
//    public void testGetPersonResponsible() {
//        System.out.println("getPersonResponsible");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getPersonResponsible();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPersonResponsible method, of class projectBean.
//     */
//    @Test
//    public void testSetPersonResponsible() {
//        System.out.println("setPersonResponsible");
//        String personResponsible = "";
//        projectBean instance = new projectBean();
//        instance.setPersonResponsible(personResponsible);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTaskName method, of class projectBean.
//     */
//    @Test
//    public void testGetTaskName() {
//        System.out.println("getTaskName");
//        projectBean instance = new projectBean();
//        String expResult = "";
//        String result = instance.getTaskName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setTaskName method, of class projectBean.
//     */
//    @Test
//    public void testSetTaskName() {
//        System.out.println("setTaskName");
//        String taskName = "";
//        projectBean instance = new projectBean();
//        instance.setTaskName(taskName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateSpecificTask method, of class projectBean.
//     */
//    @Test
//    public void testUpdateSpecificTask() {
//        System.out.println("updateSpecificTask");
//        projectBean instance = new projectBean();
//        instance.updateSpecificTask();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateDB method, of class projectBean.
//     */
//    @Test
//    public void testUpdateDB() {
//        System.out.println("updateDB");
//        projectBean instance = new projectBean();
//        instance.updateDB();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSprintCompleted method, of class projectBean.
//     */
//    @Test
//    public void testGetSprintCompleted() {
//        System.out.println("getSprintCompleted");
//        projectBean instance = new projectBean();
//        Tasks[] expResult = null;
//        Tasks[] result = instance.getSprintCompleted();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSprintInProgress method, of class projectBean.
//     */
//    @Test
//    public void testGetSprintInProgress() {
//        System.out.println("getSprintInProgress");
//        projectBean instance = new projectBean();
//        Tasks[] expResult = null;
//        Tasks[] result = instance.getSprintInProgress();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSprintNotStarted method, of class projectBean.
//     */
//    @Test
//    public void testGetSprintNotStarted() {
//        System.out.println("getSprintNotStarted");
//        projectBean instance = new projectBean();
//        Tasks[] expResult = null;
//        Tasks[] result = instance.getSprintNotStarted();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getCompletedList method, of class projectBean.
     */
    @Test
    public void testGetCompletedList() {
        System.out.println("getCompletedList");
        projectBean instance = new projectBean();
        
        Tasks[] result = instance.getCompletedList();
        
        // TODO review the generated test code and remove the default call to fail.
        if(result == null)
            fail("GetCompletedList Failed.");
    }

    /**
     * Test of getInProgressList method, of class projectBean.
     */
    @Test
    public void testGetInProgressList() {
        System.out.println("getInProgressList");
        projectBean instance = new projectBean();
        
        Tasks[] result = instance.getInProgressList();
        if(result == null)
            fail("GetCompletedList Failed.");
    }

    /**
     * Test of getNotStartedlist method, of class projectBean.
     */
    @Test
    public void testGetNotStartedlist() {
        System.out.println("getNotStartedlist");
        projectBean instance = new projectBean();
        
        Tasks[] result = instance.getNotStartedlist();
        if(result == null)
            fail("GetCompletedList Failed.");
    }
}
