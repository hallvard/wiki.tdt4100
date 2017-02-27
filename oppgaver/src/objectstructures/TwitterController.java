package objectstructures;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TwitterController {

    @FXML
    private TextField nameField;

    @FXML
    private Label account1Name;

    @FXML
    private Label account2Name;

    @FXML
    private Label account3Name;

    @FXML
    private TextField p1Partner;

    @FXML
    private TextField p2Partner;

    @FXML
    private TextField p3Partner;

    @FXML
    private ComboBox<String> p1newPartner;

    @FXML
    private ComboBox<String> p2newPartner;

    @FXML
    private ComboBox<String> p3newPartner;

    private TwitterAccount[] accounts = new TwitterAccount[3]; 
    
    private ArrayList<Tweet> tweets = new ArrayList<>();
    
    @FXML
    public void initialize(){
    	accounts[0] = new TwitterAccount("Account1");
    	accounts[1] = new TwitterAccount("Account2");
    	accounts[2] = new TwitterAccount("Account3");
    	update();
    }
    
    
    public void update(){
    	account1Name.setText(accounts[0].getUserName());
    	account2Name.setText(accounts[1].getUserName());
    	account3Name.setText(accounts[2].getUserName());
    	/*String partnerName; 
    	if(accountCount>0){
    		partnerName = accounts[0].getPartner() != null ? accounts[0].getPartner().getName() : "";  
    		account1Name.setText(accounts[0].getName());
    		p1Partner.setText(partnerName);
    		p1newPartner.setItems(comboBoxData);
    	}

    	if(accountCount>1){
    		partnerName = accounts[1].getPartner() != null ? accounts[1].getPartner().getName() : "";  
    		p2Name.setText(accounts[1].getName());
    		p2Partner.setText(partnerName);
    		p2newPartner.setItems(comboBoxData);
    	}

    	if(accountCount>2){
    		partnerName = accounts[2].getPartner() != null ? accounts[2].getPartner().getName() : "";  
    		account3Name.setText(accounts[2].getName());
    		p3Partner.setText(partnerName);
    		p3newPartner.setItems(comboBoxData);
    	}*/
    }
    /*@FXML
    void createPartner() {
    	Partner partner = new Partner(nameField.getText()); 
    	accounts[accountCount] = partner ; 
    	accountCount ++ ; 
    	comboBoxData.add(partner.getName()); 
    	update(); 
    }*/
}