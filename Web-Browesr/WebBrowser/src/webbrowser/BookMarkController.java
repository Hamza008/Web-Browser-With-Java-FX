/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webbrowser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author root
 */
public class BookMarkController implements Initializable {

     @FXML private TableView<Link>bookmarkShowTableView;
    @FXML private TableColumn<Link,String>link;
    
    DataBaseImplementation dbib = new DataBaseImplementation("BOOKMARK");
        List<Link> bookmarkList = new ArrayList<Link>();
     
     @FXML private Button bookmarkClearButton;
    
    public ObservableList<Link> list;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          bookmarkList= dbib.selectAll();
       
        list = FXCollections.observableArrayList(bookmarkList);
        link.setCellValueFactory(new PropertyValueFactory<Link,String>("link"));
       
       bookmarkShowTableView.setItems(list);
       
       
       
       bookmarkClearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                dbib.delete();
                list.clear();
                bookmarkShowTableView.setItems(list);
                
                
            }
        });
        
        
    }    
    
}
