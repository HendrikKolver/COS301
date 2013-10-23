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
public class navigationBeanTest {
    sessionBean bean = new sessionBean();
    
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
        
        bean.setProjectID("1234");
        bean.setProject(new Project());
        bean.getProject().setId("1234");
        bean.setLoggedIn(true);
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
            System.out.println("");
           if(instance.getPageToLoad().equals("planingPoker.xhtml"))
               fail("incorrect value assigned");
        }
        catch(Exception e)
        {
            fail("exception thrown");
        }
    }
    
    /**
     * Test of changeScrum method, of class navigationBean.
     */
    @Test
    public void testGetPageToLoad() {
        System.out.println("GetPageToLoad");
      
        try
        {
           bean.setLoggedIn(false);
           navigationBean instance = new navigationBean();
           
            System.out.println("");
           if(!instance.getPageToLoad().equals(""))
               fail("incorrect value assigned");
        }
        catch(Exception e)
        {
            fail("exception thrown");
        }
    }

    
}
