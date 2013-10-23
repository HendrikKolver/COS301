/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reference;

import org.junit.*;
import static org.junit.Assert.*;
import session.sessionBean;

/**
 *
 * @author hendrik
 */
public class finishSprintBeanTest {
    
    public finishSprintBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        sessionBean bean = new sessionBean();
        bean.setProjectID("1234");
        bean.setProject(new Project());
        bean.getProject().setId("1234");
        
        
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of finishSprint method, of class finishSprintBean.
     */
    @Test
    public void testFinishSprint() {
        try{
        System.out.println("finishSprint");
        finishSprintBean instance = new finishSprintBean();
        instance.finishSprint();
        }catch(Exception e)
        {
            fail("Exception thrown, FinishSprint()");
        }
    }
}
