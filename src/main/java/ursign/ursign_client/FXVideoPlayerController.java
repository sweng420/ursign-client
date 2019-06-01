package ursign.ursign_client;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import player.Player;

//Multiple videos with comboBox for video tab
public class FXVideoPlayerController {
	
	ComboBox<String> videosList;
	Player vidplayer;
	@FXML BorderPane rootBorderPane;

	public void initialize() {
		
		try{
			Canvas canvas = new Canvas(700,500);
			
			//State = 1 for videoTab
			vidplayer  = new Player(canvas,0,0, new Stage(), 1);
			vidplayer.controls.disableButtons(true);
			String [] paths = {
					//ASL Alphabet
					"https://www.youtube.com/watch?v=tkMg8g8vVUo",
					//ASL Numbers 1-30
					"https://www.youtube.com/watch?v=hFCXyB6q2nU",
					//ASL Family Signs
					"https://www.youtube.com/watch?v=XUg1eKl65p4",
					//Common Phrase (0_FULL_LENGTH.mp4)
					//"YSS_Phrase_Video.mp4"
			};
		
			//Load videos
			vidplayer.loadPaths(paths);

			//Setup ComboBox for users to select a video to play
			videosList = new ComboBox<String>();
			videosList.setPromptText("Select video...");
			videosList.prefWidthProperty().bind(rootBorderPane.widthProperty());
			videosList.setStyle("-fx-font-size: 15; -fx-text-fill: Black;");
			
			videosList.getItems().addAll(
					"ASL Alphabet",
					"ASL Numbers 1-30",
					"ASL Family Signs"
					//"YSS Phrase"
			);
			
			//Add components to scene and show stage
			rootBorderPane.setTop(videosList);
			rootBorderPane.setCenter(vidplayer);
			
			//stage.setScene(scene);
			//stage.show();

			videosList.setOnAction(e->{
				vidplayer.controls.disableButtons(false);
				vidplayer.loadVideo(videosList.getSelectionModel().getSelectedIndex());
			});
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}