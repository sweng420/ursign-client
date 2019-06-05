package ursign.ursign_client;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.javafx.geom.Rectangle;

import AudioPlayer.AudioPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
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
	private FlowPane pageVBox = new FlowPane(Orientation.VERTICAL);
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
		
		pageVBox.setVgap(8);
	    pageVBox.setHgap(4);
	    pageVBox.setAlignment(Pos.CENTER);
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

		currentPage = 0;
		selectedPresentation = cp.getPresentation();
		showPage(0);
	}
	
	public static Pane vizMultimedia(Multimedia m) {
		switch(m.getType()) {
		case image:
			Pane imgPane = new Pane();
			File imageFile = new File(Util.constructPath(MediaType.galleryImg, m.getFilelocation()));
			Image imageElement = new Image("file:"+imageFile.getAbsolutePath());
			
			ImageView iv2 = new ImageView();
	         iv2.setImage(imageElement);
	         iv2.setFitWidth(100);
	         iv2.setPreserveRatio(true);
	         iv2.setSmooth(true);
	         iv2.setCache(true);
	         
	         imgPane.getChildren().add(iv2);

	         return imgPane;
		case text:
			Pane txtPane = new Pane();
			Text textElement = new Text();
			textElement.setText(m.getFilelocation());
			textElement.setStyle(m.getStyle());
			
			txtPane.getChildren().add(textElement);

			return txtPane;
		case video:
			//Root pane
			Pane rootPane = new Pane();
			
			//Video Node
			HBox videoBox = new HBox();
			Canvas canvas = new Canvas(250,400);
			
			//State = 0 for singleVideo
			Player vidplayer  = new Player(canvas,250,400, new Stage(), 0);
			vidplayer.controls.disableButtons(true);
			String [] paths = {Util.constructPath(MediaType.videoFile, m.getFilelocation())};

			//Load videos
			vidplayer.loadPaths(paths);
			vidplayer.setMaxHeight(canvas.getHeight());
			vidplayer.setMaxWidth(canvas.getWidth());
			
			videoBox.getChildren().add(vidplayer);
			
			rootPane.getChildren().add(vidplayer);
			vidplayer.loadVideo(0);

			return rootPane;
		case audio:
			AudioPlayer audplayer = new AudioPlayer(Util.constructPath(MediaType.galleryImg, m.getFilelocation()));
			Pane audPane = audplayer.getPane();
			return audPane;
		case graphic:
			String shapeType = m.getFilelocation();
			HBox gfxPane = new HBox();
			
			switch(shapeType) {
			case "Circle":
				Circle c = graphics.GraphicsDisplay.drawCircle(0,0,100,100,new Color(0.2, 0.3, 0.6, 1));
				gfxPane.getChildren().add(c);
				return gfxPane;
			case "Rectangle":
				javafx.scene.shape.Rectangle r = graphics.GraphicsDisplay.drawRect(50, 50, 20, 30, 2, new Color(0.5, 0.4, 0.6, 1));

				gfxPane.getChildren().add(r);
				return gfxPane;
			case "Ellipse":
				Ellipse e = graphics.GraphicsDisplay.drawEllipse(50, 50, 30, 15, 2, new Color(0.2, 0.3, 0.6, 1));
				
			
				gfxPane.getChildren().add(e);
				return gfxPane;
			case "Line":
				Line l = graphics.GraphicsDisplay.drawLine(0, 0, 50, 50, 2, new Color(0.1, 0.6, 0.1, 1));

				gfxPane.getChildren().add(l);
				return gfxPane;
	
				default:
					return new Pane();
			}
	     default:
	    	 return new Pane();
	    	// break;
		}
	}
	
	private void showPage(int pageNum) {
		pageVBox.getChildren().clear();
		int i = 0;
		for(Multimedia pageMultimedia : selectedPresentation.getPages().get(pageNum).getMultimedias()) {
			Pane p = vizMultimedia(pageMultimedia);
			
			pageVBox.getChildren().add(p);
			
			i++;
		}
	}
}
