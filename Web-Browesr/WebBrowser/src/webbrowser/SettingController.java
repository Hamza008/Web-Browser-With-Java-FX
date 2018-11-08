/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webbrowser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author root
 */
public class SettingController implements Initializable {

    @FXML
        private TextField homeUrlField;
    @FXML
        private Button homeUrlSaveButton;
    
    
    private static String homeUrl= "http://www.google.com/?gws_rd=cr,ssl&ei=dQWMV7GIOYz0vgTd-LfYCg&fg=1";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        homeUrlSaveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                homeUrl=homeUrlField.getText();
                homeUrlField.setText(homeUrl);
            }
        });
        
        
        
    }    

    public  String getHomeUrl() {
        return homeUrl;
    }
    
    
    
    
}
