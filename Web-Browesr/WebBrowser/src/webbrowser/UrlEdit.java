
package webbrowser;


public class UrlEdit {
    
    public String urlEdit(String userText)
    {
        //userText= userText.trim();
        userText = userText.replaceAll("\\s","");
        if(userText.charAt(0)!='h'){
         
        String st = "https://www.";
        st+=userText;
        return st;
        }else{
            return userText;
        }
        
        
    }
    
}
