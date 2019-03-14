package ursign.ursign_client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.Node;
public class FXRegisterController {
	private int parentid = 0;
	@FXML TextField usernameTextfield;
	@FXML PasswordField passwordTextfield;
	@FXML PasswordField confirmPasswordTextfield;
	@FXML TextField emailTextfield;
	@FXML TextField realnameTextfield;
	@FXML Spinner<Integer> ageTextfield;
	@FXML ComboBox genderTextfield;
	
	public FXRegisterController(Integer parentid) {
        this.parentid = parentid;
    }

    public FXRegisterController() {
        this(0);
    }
	
    @FXML public void initialize() {
    	
    }
    
	@FXML protected void handleRegister(ActionEvent event) {
		if(usernameTextfield.getText().trim().length() > 0 &&
			passwordTextfield.getText().trim().length() > 0 &&
			confirmPasswordTextfield.getText().trim().length() > 0 &&
			emailTextfield.getText().trim().length() > 0 &&
			ageTextfield.getValue() > 5 &&
			confirmPasswordTextfield.getText().trim().contentEquals(passwordTextfield.getText().trim())) {
				Web webObject = new Web();
				
				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				urlParameters.add(new BasicNameValuePair("username", usernameTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("password", passwordTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("realname", realnameTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("email", emailTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("gender", genderTextfield.getValue().toString()));
				urlParameters.add(new BasicNameValuePair("parentid", Integer.toString(parentid)));
				urlParameters.add(new BasicNameValuePair("age", Integer.toString(ageTextfield.getValue())));
				WebRequest wr;
				
				try {
					wr = webObject.makeRequest("http://erostratus.net:5000/register", urlParameters, new ArrayList<Cookie>());
					if(!wr.hasError()) {
						System.out.println(wr.getJSON().toString());
						if(parentid > 0) {
							((Node)(event.getSource())).getScene().getWindow().hide();
						}
					} else {
						System.out.println(wr.getError());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
}
