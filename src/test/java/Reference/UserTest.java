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
public class UserTest {
    
    public UserTest() {
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
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        try{
            System.out.println("getName");
            User instance = new User();
            instance.setName("pietie");
            String expResult = "pietie";
            String result = instance.getName();
            assertEquals(expResult, result);
        }catch(Exception e)
        {
            fail("Exception thrown, GetName()");
        }
    }

   

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        try{
            System.out.println("getPassword");
            User instance = new User();
            instance.setPassword("password");
            String expResult = "password";
            String result = instance.getPassword();
            assertEquals(expResult, result);
        }catch(Exception e)
        {
            fail("Exception thrown, GetPassword()");
        }
    }

    /**
     * Test of getSurname method, of class User.
     */
    @Test
    public void testGetSurname() {
        try{
            System.out.println("GetSurname");
            User instance = new User();
            instance.setSurname("surname");
            String expResult = "surname";
            String result = instance.getSurname();
            assertEquals(expResult, result);
        }catch(Exception e)
        {
            fail("Exception thrown, GetSurname()");
        }
    }

 
    /**
     * Test of isUpdate method, of class User.
     */
    @Test
    public void testIsUpdate() {
        try{
            System.out.println("IsUpdate");
            User instance = new User();
            instance.setUpdate(true);
            Boolean expResult = true;
            Boolean result = instance.isUpdate();
            assertEquals(expResult, result);
            instance.update();
            expResult = false;
            result = instance.isUpdate();
            assertEquals(expResult, result);
        }catch(Exception e)
        {
            fail("Exception thrown, IsUpdate()");
        }
    }


    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        try{
            System.out.println("GetUsername");
            User instance = new User();
            instance.setUsername("username");
            String expResult = "username";
            String result = instance.getUsername();
            assertEquals(expResult, result);
        }catch(Exception e)
        {
            fail("Exception thrown, GetUsername()");
        }
    }

}
