/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Injection;

import org.junit.*;
import Injection.ScriptCheck;
import static org.junit.Assert.*;

/**
 *
 * @author hendrik
 */
public class ScriptCheckTest {
    
    public ScriptCheckTest() {
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
     * Test of removeScript method, of class ScriptCheck.
     */
    @Test
    public void testRemoveScript() {
        
        System.out.println("removeScript");
        String message = "<ScriPt>alert(HelloWorld!)</SCRIPT>";
        ScriptCheck s= new ScriptCheck();
        String result = s.removeScript(message);
        System.out.println("Result: "+ result);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
