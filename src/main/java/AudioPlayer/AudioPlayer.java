package AudioPlayer;
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;  
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class AudioPlayer
{
	private StackPane root = new StackPane(); 
	
	public AudioPlayer(String path){
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);  
		
		File playFile = new File("images/play-icon.png");
		Image playIcon = new Image("file:"+playFile.getAbsolutePath());
		ImageView playView = new ImageView(playIcon);
		playView.setFitHeight(50);
		playView.setFitWidth(50);
		
		File pauseFile = new File("images/pause-icon.png");
		Image pauseIcon = new Image("file:"+pauseFile.getAbsolutePath());
		ImageView pauseView = new ImageView(pauseIcon);
		pauseView.setFitHeight(50);
		pauseView.setFitWidth(50);
		
		File stopFile = new File("images/stop-2-icon.png");
		Image stopIcon = new Image("file:"+stopFile.getAbsolutePath());
		ImageView stopView = new ImageView(stopIcon);
		stopView.setFitHeight(50);
		stopView.setFitWidth(50);
		
		Button playPauseButton = new Button("", playView);  
		playPauseButton.setStyle("-fx-background-color: #FFFFFF");
		playPauseButton.setMaxHeight(40);
		playPauseButton.setMaxWidth(40);
		 playPauseButton.setOnAction(new EventHandler<ActionEvent>()
		 {  
	            @Override  
	            public void handle(ActionEvent arg0)
	            {   
	                if (mediaPlayer.getStatus().equals(Status.PLAYING))
	                {
	                	playPauseButton.setGraphic(playView);
	                	mediaPlayer.pause();
	                } else
	                {
	                	playPauseButton.setGraphic(pauseView);
	                	mediaPlayer.play();
	                }
	            }  
	        });  
		 
		 Button stopButton = new Button("", stopView);
		 stopButton.setStyle("-fx-background-color: #FFFFFF");
		 stopButton.setOnAction(new EventHandler<ActionEvent>()
		 {  
	            @Override  
	            public void handle(ActionEvent arg0)
	            {   
	                playPauseButton.setGraphic(playView);
	                mediaPlayer.stop();
	            }  
	        });  
		 
		HBox buttonBox = new HBox(1); 
		buttonBox.setStyle("-fx-background-color: #79c1e4; -fx-padding: 15; -fx-spacing: 10; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-width: 2px; -fx-border-color: black;");
		buttonBox.setMaxHeight(playPauseButton.getHeight() * 1.1);
		buttonBox.setMaxWidth(playPauseButton.getWidth() * 2.2);
		buttonBox.getChildren().addAll(playPauseButton, stopButton);
		
		
        root.getChildren().add(buttonBox);
        StackPane.setAlignment(buttonBox, Pos.CENTER);
	}
	
	public StackPane getPane() {
		return root;
	}
}
