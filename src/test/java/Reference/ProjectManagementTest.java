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
public class ProjectManagementTest {
    
    public ProjectManagementTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Project tmp = new Project();
        tmp.setId("1111");
        tmp.addUser("Jannie");
        Reference.projects.add(tmp);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class ProjectManagement.
     */
    @Test
    public void testAddUser() {
        try{
            System.out.println("addUser");
            ProjectManagement instance = new ProjectManagement();
            instance.userToAdd = "Karel";
            instance.projectID ="1111";
            instance.addUser();
            
        }catch(Exception e)
        {
            fail("AddUser(), exception thrown");
        }
    }

    /**
     * Test of removeUser method, of class ProjectManagement.
     */
    @Test
    public void testRemoveUser() {
        try{
            System.out.println("RemoveUser");
            ProjectManagement instance = new ProjectManagement();
            instance.userToRemove = "Karel";
            instance.projectID ="1111";
            instance.addUser();
            
        }catch(Exception e)
        {
            fail("RemoveUser(), exception thrown");
        }
    }

}
