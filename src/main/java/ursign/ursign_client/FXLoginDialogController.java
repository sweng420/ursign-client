package ursign.ursign_client;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
 
public class FXLoginDialogController {
	@FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Text actiontarget;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
        
        String uname = usernameField.getText();
        String password = passwordField.getText();
    }

}