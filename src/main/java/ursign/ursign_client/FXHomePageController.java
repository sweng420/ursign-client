package ursign.ursign_client;
 
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.scene.control.TableColumn;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer.Form;
import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Separator;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Modality;
import javafx.collections.ObservableList;
import javafx.beans.binding.Bindings;
//import org.JSON;

import java.util.Calendar;

public class FXHomePageController {
	private User u;
	private Integer selectedStudentId;
    @FXML private Text actiontarget;
    
    // gallerybox view
    @FXML private HBox gallerybox;
    @FXML private GridPane gallerybox_imagegrid;
    @FXML private HBox gallerybox_bigimagebox;
    @FXML private HBox gallerybox_controlbox;
    @FXML private Button gallerybox_nextbutton;
    @FXML private Button gallerybox_prevbutton;
    @FXML private Button gallerybox_slideshowbutton;
    @FXML private Button gallerybox_gallerybutton;
    @FXML private BorderPane gallerybox_borderpane;
    
    
    @FXML private Separator extraControlsSep;
    
    // profilebox view
    @FXML private HBox profilebox;
    @FXML private HBox profilebox_controlbox;
    @FXML private GridPane labels_fields;
    @FXML private Button profilebox_editbutton;
    @FXML private Button profilebox_savebutton;
    @FXML private Label usernameLabel;
    @FXML private TextField usernameTextfield;
    @FXML private Label passwordLabel;
    @FXML private PasswordField passwordTextfield;
    @FXML private Label realnameLabel;
    @FXML private TextField realnameTextfield;
    @FXML private Label ageLabel;
    @FXML private TextField ageTextfield;
    @FXML private Label emailLabel;
    @FXML private TextField emailTextfield;
    @FXML private TableView childrenTableView;
    @FXML private Label profiletitle;
    @FXML private TableColumn editcolumn;
    @FXML private Button profilebox_savestudentbutton;
    @FXML private Label message;
    
    // bottom (constant) view
    @FXML private Button gallerySelector;
    @FXML private Button profileSelector;
    
    // top (title) view
    @FXML private VBox galleryTitleBox;
    @FXML private HBox profileTitleBox;
    @FXML private ImageView iconimage;
    @FXML private StackPane titleStackPane;
    
    // tabs
    @FXML private TabPane tabpane;
    @FXML private Tab profiletab;
    @FXML private Tab gallerytab;
	
    private Image imageList[] = new Image[26];
    private ImageView imageGalleryNodeList[] = new ImageView[26];
    private ImageView imageLargeNodeList[] = new ImageView[26];
    private String[] imageInfoStrings = new String[26];
    private Label[] imageInfoLabels = new Label[26];
	private File imageFiles;
	private int gridStartIndex = 0;
	private int gridLastNodeIndex;
	private int gridNodeIndex;
	private boolean inSlideShow = false;
	private int currentSlideNode = 0;
	
	private Path dataDirectory = Paths.get(System.getProperty("user.home"), ".YSS", "ursign");
	
	public FXHomePageController(User given_user) {
        this.u = given_user ;
    }

    public FXHomePageController() {
        this(new User());
    }
	
	private void initialize_gallery() {

		try {
			for (int i = 0; i < 26; i++){
				//Folder "image" is in project folder
				imageFiles = new File("images/"+i+".jpg");
				imageList[i] = new Image("file:"+imageFiles.getAbsolutePath());
				System.out.println(imageFiles.getAbsolutePath());
				
				//Create list of ImageView nodes of size 100x100 for image gallery
				imageGalleryNodeList[i] = new ImageView(imageList[i]);
				imageGalleryNodeList[i].setPreserveRatio(false);
				imageGalleryNodeList[i].setFitWidth(150);
				imageGalleryNodeList[i].setFitHeight(150);
				
				//Create list of ImageView nodes with image ratio preserved and 300 width
				imageLargeNodeList[i] = new ImageView(imageList[i]);
				imageLargeNodeList[i].setPreserveRatio(true);
				imageLargeNodeList[i].setFitWidth(300);
				
				//Create image information for each image
				///imageInfoStrings[i] = ("This is image number: "+i);
				//imageInfoLabels[i] = new Label(imageInfoStrings[i]);
				//imageInfoLabels[i].setStyle("-fx-font-size: 20; -fx-text-fill: Black;");
			}
			
			
			fillGrid(gridStartIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML protected void handleRegisterStudent(ActionEvent event) {
		
		try {
			//Stage stage = (Stage)usernameField.getScene().getWindow();
			System.out.println("uid: "+u.getUid());
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXRegister.fxml"));     
			FXRegisterController controller = new FXRegisterController(u.getUid()); /* pass the user to the HomePage controller */
			fxmlLoader.setController(controller);
			Node root = fxmlLoader.load();			
			Scene scene = new Scene((Parent)root, 1000, 500);
			stage.setScene(scene);
			stage.setTitle("Register Student");
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner(
		        ((Node)event.getSource()).getScene().getWindow() );
			stage.show();
			
			initialize_profile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@FXML protected void handleProfileEdit(ActionEvent event) {
		for (Node child : labels_fields.getChildren()) {
            if(GridPane.getColumnIndex(child) == 1){
            	child.setVisible(!child.isVisible());
            }
        }
		
		profilebox_savebutton.setVisible(true);
		profilebox_editbutton.setVisible(false);
	}
	
	@FXML protected void handleProfileSave(ActionEvent event) {
		if(usernameTextfield.getText().trim().length() > 0 &&
			passwordTextfield.getText().trim().length() > 0 &&
			realnameTextfield.getText().trim().length() > 0 &&
			emailTextfield.getText().trim().length() > 0 &&
			Integer.parseInt(ageTextfield.getText()) >= 5) {
				Web webObject = new Web();
				
				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				urlParameters.add(new BasicNameValuePair("username", usernameTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("password", passwordTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("realname", realnameTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("email", emailTextfield.getText().trim()));
				urlParameters.add(new BasicNameValuePair("born", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(ageTextfield.getText()))));
				System.out.println("ageTextfield: "+ ageTextfield.getText());
				System.out.println("sends: "+ Integer.toString(Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(ageTextfield.getText())));
				WebRequest wr;
				
				try {
					wr = webObject.makeRequest("/updateinfo", urlParameters, u.getCookies());
					if(!wr.hasError()) {
						for (Node child : labels_fields.getChildren()) {
				            if(GridPane.getColumnIndex(child) == 1){
				            	child.setVisible(!child.isVisible());
				            }
				        }
						
						initialize_profile();
						
						profilebox_savebutton.setVisible(false);
						profilebox_editbutton.setVisible(true);
						
						
					} else {
						System.out.println(wr.getError());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		else {
			message.setText("There was an error in the form.  Please complete all fields");
		}
				
	}
	
	
	@FXML protected void handleProfileMyStudents(ActionEvent event) {
		
	}
	
	@FXML protected void handleSelectProfile(ActionEvent event) {
		gallerybox_controlbox.setVisible(false);
		profilebox_controlbox.setVisible(true);
	}
	
	@FXML
    void event(Event ev) {
        if (gallerytab.isSelected()) {
            System.out.println("Tab is Selected");
        }
    }
	
	@FXML protected void handleSelectGallery(ActionEvent event) {
		inSlideShow = false;
		gallerybox_borderpane.setCenter(gallerybox_imagegrid);
		gallerybox_slideshowbutton.setDisable(false);
		gallerybox_gallerybutton.setDisable(true);
		gallerybox_borderpane.setRight(null);
	}
	@FXML protected void handleGalleryNext(ActionEvent event) {
		if (gridLastNodeIndex != imageGalleryNodeList.length && inSlideShow == false){
			emptyGrid();
			gridStartIndex = gridStartIndex+3;
			fillGrid(gridStartIndex);
		} else if(currentSlideNode < (imageGalleryNodeList.length-1) && inSlideShow == true){
			currentSlideNode++;
			enterSlideShow(currentSlideNode);
		}
	}
	
	@FXML protected void handleGalleryPrev(ActionEvent event) {
		if (gridStartIndex != 0 && inSlideShow == false){
			emptyGrid();
			gridStartIndex = gridStartIndex-3;
			fillGrid(gridStartIndex);
		} else if(currentSlideNode != 0 && inSlideShow == true){
			currentSlideNode--;
			enterSlideShow(currentSlideNode);
		}
	}
	
	@FXML protected void handleGallerySlideshow(ActionEvent event) {
		currentSlideNode = 0;
		enterSlideShow(currentSlideNode);
		gallerybox_slideshowbutton.setDisable(true);
		gallerybox_gallerybutton.setDisable(false);
	}
	
	@FXML protected void handleGalleryGallery(ActionEvent event) {
		inSlideShow = false;
		gallerybox_borderpane.setCenter(gallerybox_imagegrid);
		gallerybox_slideshowbutton.setDisable(false);
		gallerybox_gallerybutton.setDisable(true);
		gallerybox_borderpane.setRight(null);
	}
	
	@FXML protected void clickGalleryGrid(MouseEvent event) {
		Node node  = event.getPickResult().getIntersectedNode();
		if (node != gallerybox_imagegrid) {
	        int colIndex = GridPane.getColumnIndex(node);
	        int rowIndex = GridPane.getRowIndex(node);
	        gridNodeIndex = getGirdNum(colIndex, rowIndex);
	        enterSlideShow(gridStartIndex+gridNodeIndex);
	        gallerybox_slideshowbutton.setDisable(true);
	        gallerybox_gallerybutton.setDisable(false);
	    }
	}
	
	@FXML protected void handleSaveStudent(ActionEvent event) {
		if(usernameTextfield.getText().trim().length() > 0 &&
				passwordTextfield.getText().trim().length() > 0 &&
				realnameTextfield.getText().trim().length() > 0 &&
				emailTextfield.getText().trim().length() > 0 &&
				Integer.parseInt(ageTextfield.getText()) >= 5) {
					Web webObject = new Web();
					
					List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
					urlParameters.add(new BasicNameValuePair("username", usernameTextfield.getText().trim()));
					urlParameters.add(new BasicNameValuePair("password", passwordTextfield.getText().trim()));
					urlParameters.add(new BasicNameValuePair("realname", realnameTextfield.getText().trim()));
					urlParameters.add(new BasicNameValuePair("email", emailTextfield.getText().trim()));
					urlParameters.add(new BasicNameValuePair("born", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(( ageTextfield).getText()))));
					urlParameters.add(new BasicNameValuePair("studentid", Integer.toString(selectedStudentId)));
					WebRequest wr;
					
					try {
						wr = webObject.makeRequest("/updatestudentinfo", urlParameters, u.getCookies());
						if(!wr.hasError()) {
							for (Node child : labels_fields.getChildren()) {
					            if(GridPane.getColumnIndex(child) == 1){
					            	child.setVisible(!child.isVisible());
					            }
					        }
							
							/* re-init the parent profile */
							initialize_profile();
							
							profilebox_savebutton.setVisible(false);
							profilebox_editbutton.setVisible(true);
							profilebox_savestudentbutton.setVisible(false);
							
						} else {
							System.out.println(wr.getError());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
					
		}	
	

	private void initialize_profile() {
		profiletitle.setText(u.getUsername());
		Image imageVar;
		File imageFile;
		ImageView imageViewFile;
		imageFile = new File("images/icon.jpg");
		imageVar = new Image("file:"+imageFile.getAbsolutePath());
		
		//Create list of ImageView nodes of size 100x100 for image gallery
		imageViewFile = new ImageView(imageVar);
		imageViewFile.setPreserveRatio(false);
		imageViewFile.setFitWidth(150);
		imageViewFile.setFitHeight(150);
		
		iconimage.setImage(imageVar);
		
		Web webObject = new Web();
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("x", "y"));
		WebRequest wr;
		System.out.println(getUser().getUsername());
		try {
			/* populate the table at the top of the profile page */
			wr = webObject.makeRequest("/myinfo", urlParameters, u.getCookies());
			if(!wr.hasError()) {
				profiletitle.setText(wr.getJSON().getAsJsonObject("user").get("username").getAsString());
				u.setUsername(wr.getJSON().getAsJsonObject("user").get("username").getAsString());
				u.setRealname(wr.getJSON().getAsJsonObject("user").get("realname").getAsString());
				u.setEmail(wr.getJSON().getAsJsonObject("user").get("email").getAsString());
				u.setBorn(Integer.parseInt(wr.getJSON().getAsJsonObject("user").get("born").getAsString()));
				u.setAge(Calendar.getInstance().get(Calendar.YEAR) - u.getBorn());
				usernameTextfield.setText(u.getUsername());
				usernameLabel.setText(u.getUsername());
				passwordTextfield.setText("");
				passwordLabel.setText("*****");
				realnameTextfield.setText(u.getRealname());
				realnameLabel.setText(u.getRealname());
				emailTextfield.setText(u.getEmail());
				emailLabel.setText(u.getEmail());
				ageLabel.setText(Integer.toString(u.getAge()));
				ageTextfield.setText(Integer.toString(u.getAge()));
				/*JsonElement children = wr.getJSON().get("children");
				Type listType = new TypeToken<List<User>>() {}.getType();
				List<User> childrenList = new Gson().fromJson(children, listType);
				
				if(children != null) {
					for(User child : childrenList) {
						System.out.println(child.getUid());
					}
				}*/
				
				/* Unfortunately gson has horrible support (see commented out code above) for reading through
				 * a JSON array, so we're using org.JSON for this limited parsing
				 */

				 JSONObject jo = new JSONObject(wr.getJSONString());
				 JSONArray ja = jo.getJSONArray("children");
				
				ObservableList<User> childUsers = childrenTableView.getItems();
				childrenTableView.getItems().clear();

				childUsers.removeAll();
				for(int j = 0; j < ja.length(); j++) {
					childUsers.add(new User(ja.getJSONObject(j).getString("username"),
											ja.getJSONObject(j).getString("email"),
											ja.getJSONObject(j).getInt("born"),
											ja.getJSONObject(j).getString("realname"),
											ja.getJSONObject(j).getInt("id")));
				}
				
				/* "hack" to set the size of the table */
				childrenTableView.setFixedCellSize(25);
				childrenTableView.prefHeightProperty().bind(Bindings.size(childrenTableView.getItems()).multiply(childrenTableView.getFixedCellSize()).add(30));
				
				editcolumn.setCellFactory(ActionButtonTableCell.<User>forTableColumn("Edit", (User student) -> {
				    /*childrenTableView.getItems().remove(u);
				    return u;*/
					System.out.println("Editing "+student.getUsername());
					profiletitle.setText("Editing: "+student.getUsername());
					profilebox_savestudentbutton.setVisible(true);
					selectedStudentId = student.getUid();
					
					
					usernameTextfield.setText(student.getUsername());
					usernameLabel.setText(student.getUsername());
					passwordTextfield.setText("");
					passwordLabel.setText("*****");
					realnameTextfield.setText(student.getRealname());
					realnameLabel.setText(student.getRealname());
					emailTextfield.setText(student.getEmail());
					emailLabel.setText(student.getEmail());
					
					for (Node child : labels_fields.getChildren()) {
			            if(GridPane.getColumnIndex(child) == 1){
			            	child.setVisible(!child.isVisible());
			            }
			        }
					
					return student;
				}));    
				
			} else {
				System.out.println(wr.getError());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		initialize_gallery();
		initialize_profile();

        for (int i = 0; i < 5; i++) {
            Tab tab = new Tab();
            tab.setText("Tab" + i);
            HBox hbox = new HBox();
            hbox.getChildren().add(new Label("Tab" + i));
            hbox.setAlignment(Pos.CENTER);
            tab.setContent(hbox);
            tabpane.getTabs().add(tab);
        }


	}
	
	public void fillGrid(int start){
		for (int j = 0; j < 3; j++){
			for (int k = 0; k < 3; k++){
				gallerybox_imagegrid.add(imageGalleryNodeList[start], k, j);
				start++;
				if (start == imageGalleryNodeList.length){
					break;
				}
			}
		}
		gridLastNodeIndex = start;
	}
	
	
	public void emptyGrid(){
		gallerybox_imagegrid.getChildren().removeAll(imageGalleryNodeList);
	}
	
	public int getGirdNum(int col, int row){		
		return (3*row) + col;
	}
	
	public void enterSlideShow(int startImageIndex){
		inSlideShow = true;
		if (!(gallerybox_bigimagebox.getChildren().isEmpty())){
			gallerybox_bigimagebox.getChildren().remove(0);
		}
		gallerybox_bigimagebox.getChildren().add(imageLargeNodeList[startImageIndex]);
		
		currentSlideNode = startImageIndex;
		
		gallerybox_borderpane.setCenter(gallerybox_bigimagebox);
		gallerybox_borderpane.setRight(imageInfoLabels[startImageIndex]);
	}
    
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
    	actiontarget.setText(u.getUsername());
    }

    public void setUser(User u){
    	this.u = u;
    }
    
    public User getUser() {
    	return u;
    }
}