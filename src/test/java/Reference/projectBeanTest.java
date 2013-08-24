/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Hendrik
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
