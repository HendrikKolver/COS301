/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Injection;

/**
 *
 * @author hendrik
 */
public class ScriptCheck {
    
    public String removeScript(String message)
    {
        String original = message;
        message = message.toLowerCase();
        System.out.println("old Message: "+ message);
        message = message.replaceAll("<script>", "");
        message = message.replaceAll("</script>", "");
        System.out.println("New Message: "+ message);
        if(original.length() == message.length())
            return original;
        else
            return message;
    }
    
}
