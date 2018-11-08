/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webbrowser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author root
 */
public class MainPageController implements Initializable {

    
    @FXML
        private TextField userUrlField;
    @FXML
        private Button goButton;
    @FXML
        private Button reloadButton;
    @FXML
        private Button forwardButton;
    @FXML
        private Button backButton;
    @FXML
        private Button homeButton;
    @FXML
        private Button bookmarkAddButton;
    @FXML
        private Button bookmarkShowButton;
    @FXML
        private Button historyShowButton;
    @FXML
        private Button settingButton;
    @FXML
        private WebView webView;
      private int flag=0;
       private String userUrl;
       private String updateUrl;
       private String homeUrl ="https://www.google.com/?gws_rd=cr,ssl&ei=dQWMV7GIOYz0vgTd-LfYCg&fg=1";
       private String lastUrl;
       DataBaseImplementation dbih= new DataBaseImplementation("HISTORY");
      DataBaseImplementation dbib= new DataBaseImplementation("BOOKMARK");
       Link link = new Link();
       BackForwordControl bfc=new BackForwordControl();
    String bfstring=null;
    
     @Override // webpage view and load;
    public void initialize(URL location, ResourceBundle resources) {
       
      final WebEngine webEngine = webView.getEngine();
        webEngine.load(homeUrl);//for starting time load.
        userUrlField.setText(homeUrl);
        lastUrl=homeUrl;      
        bfc.set_back(lastUrl); 
        bfc.set_forword();
        
      
        EventHandler<ActionEvent> toEnter= new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             
               userUrl=userUrlField.getText();
               UrlEdit ue = new UrlEdit();
               userUrl=ue.urlEdit(userUrl);
                lastUrl=userUrl;
                webEngine.load(userUrl);
                webEngine.setJavaScriptEnabled(true);     
               // bfstring=ue.urlEdit(bfstring);
               // System.out.println(bfstring);
                bfc.set_back(lastUrl);
                bfc.set_forword();
                
            }
        };
        
        
        
        userUrlField.setOnAction(toEnter);
        goButton.setOnAction(toEnter);
        
        
        //webEngine theke update link collection.
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            
             public void changed() {
                    flag=0;         
                    System.out.println("this is printed");
             }
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                userUrlField.setText(newValue);
                updateUrl = newValue;
               if(flag==0){
                   lastUrl=updateUrl;
                   userUrlField.setText(lastUrl);
                flag=1;
                 System.out.println(lastUrl);
               link.setLink(updateUrl);
                bfc.set_back(lastUrl);
                bfc.set_forword();
               }
               
                dbih.insert(link);
             //insert database history table
               // System.out.println(newValue);
               
                
            }
        });
        
        
         
        
        reloadButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              webEngine.reload();
          }
      });;
   
     
       forwardButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
               bfstring=lastUrl;
               bfstring=bfc.get_forword(lastUrl);
               userUrlField.setText(bfstring);
               webEngine.load(bfstring);
               lastUrl=bfstring;
               webEngine.setJavaScriptEnabled(true);           
          }
      });
        
      
       backButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
               UrlEdit ue = new UrlEdit();
            
                bfstring=bfc.get_back(lastUrl);
                userUrlField.setText(bfstring);
                webEngine.load(bfstring);
                webEngine.setJavaScriptEnabled(true);
            //  System.out.println(bfstring);
           }
      });
       
       homeButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              SettingController sc = new SettingController();
              homeUrl= sc.getHomeUrl();
              lastUrl=homeUrl;
             webEngine.load(homeUrl);
             webEngine.setJavaScriptEnabled(true);
          }
      });
       
       
       settingButton.setOnAction(new EventHandler<ActionEvent>(){
          Stage stage;
          Parent root;
          //Scene scene;
           
           @Override
          public void handle(ActionEvent event) {
              
              try {
                  
                  stage = new Stage();
                  
                  //stage = (Stage) settingButton.getScene().getWindow();
                  root = FXMLLoader.load(getClass().getResource("Setting.fxml"));
                  Scene scene = new Scene(root);
                  stage.setScene(scene);
                  scene.getStylesheets().add(getClass().getResource("setting.css").toExternalForm());
                  stage.initModality(Modality.APPLICATION_MODAL);
                  stage.initOwner(settingButton.getScene().getWindow());
                  stage.setTitle("Setting");
                  stage.show();
                 // scene = new Scene(root);
              } catch (IOException ex) {
                  Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
              }
        
        
       
              
          }
      });
       
       
      
       bookmarkAddButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             
               link.setLink(updateUrl);
                dbib.insert(link);
          //insert database Bookmark table
              
          }
      });
       
       
       bookmarkShowButton.setOnAction(new EventHandler<ActionEvent>() {
           Stage stage;
           Parent root;
           
           @Override
          public void handle(ActionEvent event) {
              
              try {
                  
                  stage = new Stage();
                  
                  //stage = (Stage) settingButton.getScene().getWindow();
                  root = FXMLLoader.load(getClass().getResource("BookMark.fxml"));
                  Scene scene = new Scene(root);
                  stage.setScene(scene);
                  scene.getStylesheets().add(getClass().getResource("bookmark.css").toExternalForm());
                  stage.initModality(Modality.APPLICATION_MODAL);
                  stage.initOwner(settingButton.getScene().getWindow());
                   stage.setTitle("BookMark");
                  stage.show();
                 // scene = new Scene(root);
              } catch (IOException ex) {
                  Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
              } 
              
              
              
          }
      });
       
       
       historyShowButton.setOnAction(new EventHandler<ActionEvent>() {
           Stage stage;
          Parent root;
           
           @Override
          public void handle(ActionEvent event) {
              
              
              try {
                  
                  stage = new Stage();
                  
                  //stage = (Stage) settingButton.getScene().getWindow();
                  root = FXMLLoader.load(getClass().getResource("History.fxml"));
                  Scene scene = new Scene(root);
                  stage.setScene(scene);
                  scene.getStylesheets().add(getClass().getResource("history.css").toExternalForm());
                  stage.initModality(Modality.APPLICATION_MODAL);
                  stage.initOwner(settingButton.getScene().getWindow());
                   stage.setTitle("History");
                  stage.show();
                 // scene = new Scene(root);
              } catch (IOException ex) {
                  Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              
              
          }
      });
       
       
       
         
    }
     
    
}
