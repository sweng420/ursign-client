package ursign.ursign_client;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import AudioPlayer.AudioPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import player.Player;

public class FXSlideshowController {
	@FXML BorderPane rootBorderPane;
	@FXML BorderPane collectionBorderPane;
	@FXML Button slide_prevbutton;
	@FXML Button slide_nextbutton;
	private Presentation selectedPresentation;
	private int currentPage;
	private ComboBox<String> collectionsList;
	private GridPane pageVBox = new GridPane();
	private List<String> myCollections = new ArrayList<String>();
	
	public void initialize() {
		myCollections.add("Collection1.xml");
		//Setup ComboBox for users to select a video to play
		collectionsList = new ComboBox<String>();
		collectionsList.setPromptText("Select video...");
		collectionsList.prefWidthProperty().bind(rootBorderPane.widthProperty());
		collectionsList.setStyle("-fx-font-size: 15; -fx-text-fill: Black;");
		
		collectionsList.getItems().addAll(
				"Collection one"
				//,"Collection two"
		);
		
		//Add components to scene and show stage
		rootBorderPane.setTop(collectionsList);
		
		//When ComboBox value changes, load the selected Video
		collectionsList.setOnAction(e->{
			//vidplayer.controls.disableButtons(false);
			//vidplayer.loadVideo(videosList.getSelectionModel().getSelectedIndex());
			loadCollection(collectionsList.getSelectionModel().getSelectedIndex());
			loadControls();
		});
	}
	
	private void loadControls() {
		slide_nextbutton.setDisable(false);
	}
	
	@FXML protected void handleSlidePrev(ActionEvent event) {
		slide_nextbutton.setDisable(false);
		if(currentPage == 1) {
			slide_prevbutton.setDisable(true);
		}
		showPage(--currentPage);
	}
	
	@FXML protected void handleSlideNext(ActionEvent event) {
		slide_prevbutton.setDisable(false);
		
		if(currentPage == selectedPresentation.getPages().size()-2) {
			/* on the last slide, disable next button */
			slide_nextbutton.setDisable(true);
		}
		showPage(++currentPage);
	}
	
	private void loadCollection(int id) {
		pageVBox.getChildren().clear();
		rootBorderPane.setCenter(pageVBox);
		
		CollectionParser cp = new CollectionParser(myCollections.get(id));
		cp.parse();
		//assertEquals(cp.getPresentation().getPages().size(), 3);
		currentPage = 0;
		selectedPresentation = cp.getPresentation();
		showPage(0);
	}
	
	private void showPage(int pageNum) {
		pageVBox.getChildren().clear();
		for(Multimedia pageMultimedia : selectedPresentation.getPages().get(pageNum).getMultimedias()) {
			switch(pageMultimedia.getType()) {
			case "Image":
				File imageFile = new File(pageMultimedia.getFilelocation());
				Image imageElement = new Image("file:"+imageFile.getAbsolutePath());
				
				ImageView iv2 = new ImageView();
		         iv2.setImage(imageElement);
		         iv2.setFitWidth(100);
		         iv2.setPreserveRatio(true);
		         iv2.setSmooth(true);
		         iv2.setCache(true);
		         
		         pageVBox.getChildren().add(iv2);
		         break;
			case "Text":
				Text textElement = new Text();
				textElement.setText(pageMultimedia.getFilelocation());
				textElement.setStyle(pageMultimedia.getStyle());
				
				pageVBox.getChildren().add(textElement);
				break;
			case "Video":
				//Root pane
				Pane rootPane = new Pane();
				
				//Video Node
				HBox videoBox = new HBox();
				Canvas canvas = new Canvas(250,400);
				
				//State = 0 for singleVideo
				Player vidplayer  = new Player(canvas,250,400, new Stage(), 0);
				vidplayer.controls.disableButtons(true);
				String [] paths = {pageMultimedia.getFilelocation()};

				//Load videos
				vidplayer.loadPaths(paths);
				vidplayer.setMaxHeight(vidplayer.getHeight());
				vidplayer.setMaxWidth(vidplayer.getWidth());
				
				videoBox.getChildren().add(vidplayer);
				
				rootPane.getChildren().add(vidplayer);
				vidplayer.loadVideo(0);
				GridPane.setRowIndex(rootPane, 0);
				pageVBox.getChildren().add(rootPane);
				
				break;
			case "Audio":
				AudioPlayer audplayer = new AudioPlayer(pageMultimedia.getFilelocation());
				Pane audPane = audplayer.getPane();
				GridPane.setRowIndex(audPane, 1);
				pageVBox.getChildren().add(audPane);
				break;
		     default:
		    	 break;
			}
		}
	}
}
