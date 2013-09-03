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
public class navigationBeanTest {
    
    public navigationBeanTest() {
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
     * Test of changeScrum method, of class navigationBean.
     */
    @Test
    public void testChangeScrum() {
        System.out.println("changeScrum");
        String pageToLoad = "planingPoker.xhtml";
        try
        {
           navigationBean instance = new navigationBean();
           instance.changeScrum(pageToLoad); 
           
           if(!instance.getPageToLoad().equals("planingPoker.xhtml") || !instance.isScrumPage())
               fail("incorrect value assigned");
        }
        catch(Exception e)
        {
            fail("exception thrown");
        }
    }

    
}
