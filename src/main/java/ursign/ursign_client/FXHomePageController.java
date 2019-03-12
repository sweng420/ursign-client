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


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.nio.file.Path;
import java.nio.file.Paths;
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

public class FXHomePageController {
	private User u;
    @FXML private Text actiontarget;
    
    @FXML private GridPane gallerybox_imagegrid;
    @FXML private HBox gallerybox_bigimagebox;
    @FXML private HBox controlBar;
    @FXML private Button gallerybox_nextbutton;
    @FXML private Button gallerybox_prevbutton;
    @FXML private Button gallerybox_slideshowbutton;
    @FXML private Button gallerybox_gallerybutton;
    @FXML private BorderPane gallerybox_borderpane;
	
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
				imageInfoStrings[i] = ("This is image number: "+i);
				imageInfoLabels[i] = new Label(imageInfoStrings[i]);
				imageInfoLabels[i].setStyle("-fx-font-size: 20; -fx-text-fill: Black;");
			}
			
			
			fillGrid(gridStartIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	
	

	private void initialize_profile() {}
	
	public void initialize() {
		System.out.println("!!");
		initialize_gallery();
		initialize_profile();
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
		
		int gridPos = 0;
		
		gridPos = (3*row) + col;
		
		return gridPos;
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