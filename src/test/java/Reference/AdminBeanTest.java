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
public class AdminBeanTest {
    
    public AdminBeanTest() {
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
     * Test of updateUser method, of class AdminBean.
     */
    @Test
    public void testUpdateUser() {
        try{
            System.out.println("UpdateUser");
            AdminBean instance = new AdminBean();
            instance.setUserToAddUsername("Username");
            instance.setUserToAddPassword("Password");
            instance.setUserToAddName("Name");
            instance.setUserToAddSurname("Surname");
            instance.addUser();
            
            instance.setOldUsername("Username");
            instance.setUserToAddPassword("Password1");
            instance.setUserToAddName("Name1");
            instance.setUserToAddSurname("Surname1");
            instance.addUser();
            boolean success = false;
            
            for (int i = 0; i < Reference.getUsernames().size(); i++) {
                if(Reference.getUsernames().get(i).getName().equals("Name1") && Reference.getUsernames().get(i).getSurname().equals("Surname1") && Reference.getUsernames().get(i).getUsername().equals("Username") && Reference.getUsernames().get(i).getPassword().equals("Password1"))
                    success = true;
            }
            
            if(!success)
            {
              fail("Update user failed"); 
            }
        }
        catch(Exception e)
        {
            
            fail("Update user failed");
        }
    }

    /**
     * Test of addUser method, of class AdminBean.
     */
    @Test
    public void testAddUser() {
        try{
            System.out.println("addUser");
            AdminBean instance = new AdminBean();
            instance.setUserToAddUsername("Username");
            instance.setUserToAddPassword("Password");
            instance.setUserToAddName("Name");
            instance.setUserToAddSurname("Surname");
            instance.addUser();
            boolean success = false;
            for (int i = 0; i < Reference.getUsernames().size(); i++) {
                if(Reference.getUsernames().get(i).getName().equals("Name") && Reference.getUsernames().get(i).getSurname().equals("Surname") && Reference.getUsernames().get(i).getUsername().equals("Username") && Reference.getUsernames().get(i).getPassword().equals("Password"))
                    success = true;
            }
            
            if(!success)
            {
              fail("Add user failed"); 
            }
        }
        catch(Exception e)
        {
            fail("Add user failed");
        }
    }

    /**
     * Test of addProject method, of class AdminBean.
     */
    @Test
    public void testAddProject() {
        try{
        System.out.println("addProject");
        AdminBean instance = new AdminBean();
        instance.addProject();
        // TODO review the generated test code and remove the default call to fail.
        }catch(Exception e)
        {
            fail("Add project failed");
        }
    }

    /**
     * Test of getAllUsernames method, of class AdminBean.
     */
    @Test
    public void testGetAllUsernames() {
        try{
        System.out.println("getAllUsernames");
        AdminBean instance = new AdminBean();
       
        Object[] result = instance.getAllUsernames();
        if(result == null)
            fail("GetAllUsernames failed");
        // TODO review the generated test code and remove the default call to fail.
        }catch(Exception e)
        {
            fail("GetAllUsernames failed");
        }
    }

    /**
     * Test of getAllProjects method, of class AdminBean.
     */
    @Test
    public void testGetAllProjects() {
        try{
        System.out.println("getAllProjects");
        AdminBean instance = new AdminBean();
        
        Object[] result = instance.getAllProjects();
        
        
        if(result == null)
            fail("GetAllProjects failed");
        }catch(Exception e)
        {
            fail("GetAllProjects failed");
        }
    }
}
