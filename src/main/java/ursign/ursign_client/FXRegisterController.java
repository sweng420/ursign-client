package ursign.ursign_client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
public class FXRegisterController {
	private int parentid = 0;
	@FXML TextField usernameTextfield;
	@FXML PasswordField passwordTextfield;
	@FXML PasswordField confirmPasswordTextfield;
	@FXML TextField emailTextfield;
	@FXML TextField realnameTextfield;
	@FXML TextField ageTextfield;
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
			Integer.parseInt(ageTextfield.getText()) > 5 &&
			confirmPasswordTextfield.getText().trim().contentEquals(passwordTextfield.getText().trim())) {
				Web webObject = new Web();
				
				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				urlParameters.add(new BasicNameValuePair("username", usernameTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("password", passwordTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("realname", realnameTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("email", emailTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("gender", genderTextfield.getValue().toString()));
				urlParameters.add(new BasicNameValuePair("parentid", Integer.toString(parentid)));
				urlParameters.add(new BasicNameValuePair("age", ageTextfield.getText()));
				WebRequest wr;
				
				try {
					wr = webObject.makeRequest("/register", urlParameters, new ArrayList<Cookie>());
					if(!wr.hasError()) {
						System.out.println(wr.getJSON().toString());
						if(parentid > 0) {
							((Node)(event.getSource())).getScene().getWindow().hide();
						}
						else {
							/* we've just registered a new non-child account, so log into it */
								String uname = usernameTextfield.getText();
					    			String password = passwordTextfield.getText();
					    			
					    			webObject = new Web();
					    			
					    			urlParameters = new ArrayList<NameValuePair>();
					    			urlParameters.add(new BasicNameValuePair("username", uname));
					    			urlParameters.add(new BasicNameValuePair("password", password));

									try {
										wr = webObject.makeRequest("/login", urlParameters, new ArrayList<Cookie>());
										
										if(!wr.hasError()){
											System.out.println(Integer.decode(wr.getJSON().get("userid").toString()));
											User u = new User();
											u.setCookies(wr.getCookies());
											u.setUsername(uname);
											u.setUid(Integer.decode(wr.getJSON().get("userid").toString()));
											
											Stage stage = (Stage)usernameTextfield.getScene().getWindow();
											FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXHomePage.fxml"));     
											FXHomePageController controller = new FXHomePageController(u); /* pass the user to the HomePage controller */
											fxmlLoader.setController(controller);
											Node root = fxmlLoader.load();			
											Scene scene = new Scene((Parent)root, 1000, 725); 
											stage.setScene(scene);    
											stage.show();
									
								} else {
									//actiontarget.setText(wr.getError());
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
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