package Reference;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "textChat")
public class textChatBean 
{
    String htmlContent = "";
    String cheatVar;

    public String getCheatVar() {
        System.out.println("Get content cheatVar: " + textChatHolder.htmlContent);
        return textChatHolder.htmlContent;
    }

    public void setCheatVar(String cheatVar) {
        this.cheatVar = cheatVar;
    }
    
    public String getHtmlContent()
    {
        System.out.println("Get content: " + textChatHolder.htmlContent);
        return textChatHolder.htmlContent;
    }
    public void setHtmlContent(String html)
    {
        System.out.println("Set content: " + html);
        textChatHolder.htmlContent += html;
    } 
    
    public void demoFunct()
    {
        //System.out.println("called!"); 
    }
    
}
