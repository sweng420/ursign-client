package ursign.ursign_client;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import AudioPlayer.AudioPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import player.Player;
import quiz.Answer;
import quiz.Question;
import quiz.Quiz;
import quiz.QuizEngine;
import quiz.QuizParser;

public class FXQuizController {
	@FXML BorderPane rootBorderPane;
	@FXML BorderPane collectionBorderPane;
	@FXML Button slide_prevbutton;
	@FXML Button slide_nextbutton;
	@FXML HBox controlbox;
	@FXML Label message; /* for displaying messages to user */
	@FXML VBox topVbox;
	
	private Quiz selectedQuiz;
	private int currentQuestion;
	private ComboBox<String> quizzesList;
	private FlowPane questionVBox = new FlowPane(Orientation.VERTICAL);
	private FlowPane answerVBox = new FlowPane(Orientation.VERTICAL);
	private List<String> myQuizzes = new ArrayList<String>();
	private QuizEngine qe;
	private User u;
	
	public void setUser(User u) {
		this.u = u;
	}
	
	public void initialize() {
		myQuizzes.add("SignBasicsQuiz.xml");
		//Setup ComboBox for users to select a video to play
		quizzesList = new ComboBox<String>();
		quizzesList.setPromptText("Select video...");
		quizzesList.prefWidthProperty().bind(rootBorderPane.widthProperty());
		quizzesList.setStyle("-fx-font-size: 15; -fx-text-fill: Black;");
		
		quizzesList.getItems().addAll(
				"Sign Language Basics"
				//,"Collection two"
		);
		topVbox.getChildren().add(0, quizzesList);
		
		//Add components to scene and show stage
		//rootBorderPane.setTop(topBox);
		
		//When ComboBox value changes, load the selected Video
		quizzesList.setOnAction(e->{
			//vidplayer.controls.disableButtons(false);
			//vidplayer.loadVideo(videosList.getSelectionModel().getSelectedIndex());
			loadQuiz(quizzesList.getSelectionModel().getSelectedIndex());
			loadControls(quizzesList.getSelectionModel().getSelectedIndex());
		});
		
		questionVBox.setVgap(8);
	    questionVBox.setHgap(4);
	}
	
	private void loadControls(int quizid) {
		/*slide_nextbutton.setDisable(false);*/
		//Quiz q = myQuizzes.get(quizid);
		
	}
	
	@FXML protected void handleQuestionPrev(ActionEvent event) {
		slide_nextbutton.setDisable(false);
		if(currentQuestion == 1) {
			//.setDisable(true);
		}
		//showCard(--currentQuestion);
	}
	
	@FXML protected void handleQuestionNext(ActionEvent event) {
		slide_prevbutton.setDisable(false);
		
		if(currentQuestion == selectedQuiz.getQuestions().size()-2) {
			/* on the last slide, disable next button */
			//quiz_nextbutton.setDisable(true);
		}
		//showCard(++currentQuestion);
	}
	
	private void loadQuiz(int id) {
		questionVBox.getChildren().clear();

		/*QuizParser qp = new QuizParser(Util.constructPath(MediaType.quizXML, myQuizzes.get(id)));
		qp.parse();

		currentQuestion = 0;
		selectedQuiz = qp.getQuiz();*/
		System.out.println("jjj");
		qe = new QuizEngine(2, Util.constructPath(MediaType.quizXML, myQuizzes.get(id)));
		showCard(qe.getNextQuestion());
	}
	
	
	public void finishedQuiz() {
		rootBorderPane.setCenter(null);
		controlbox.getChildren().clear();
		rootBorderPane.setBottom(controlbox);
		
		message.setText("Congratulations on completing the quiz.\nx");
		u.addCredits(5);
	}
	
	
	/* showCard will show both a question part and the answer part
	 * depending on what kind of question we're showing
	 */
	private void showCard(Question selectedQuestion) {
		rootBorderPane.setCenter(null);
		controlbox.getChildren().clear();
		rootBorderPane.setBottom(controlbox);
		
		VBox cardBox = new VBox();
		cardBox.setAlignment(Pos.CENTER);
		int i = 0;
		
		
		
		/* for the answers we show them differently;
		 * multiple choice: set up a pane so we can see [A1_multimedia] [A2_multimedia] ...
		 * fill in the blank: show no answer, but reveal the input bar
		 * anki: show the multimedia exactly as we would on a slideshow slide
		 */
		if(selectedQuestion.isAnki()){
			
			for(Multimedia m : selectedQuestion.getContent()) {
				Pane p = FXSlideshowController.vizMultimedia(m);
				
				cardBox.getChildren().add(p);
			}
			Button flipButton = new Button("Flip card");
			flipButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {   			    
			    	// get rid of the flip button
			    	controlbox.getChildren().clear();
			    	
			    	FlowPane slideshowDisplay = new FlowPane(Orientation.VERTICAL);
					for(Multimedia m : selectedQuestion.getAnswers().get(0).getMultimedias()) {
						Pane p = FXSlideshowController.vizMultimedia(m);
						
						slideshowDisplay.getChildren().add(p);
					}
					cardBox.getChildren().add(new Separator());
					cardBox.getChildren().add(slideshowDisplay);
			    	
			    	// replace with easy/hard buttons
			    	Button hardButton = new Button("That was hard.");
					Button easyButton = new Button("Easy!");
					
					hardButton.setOnAction(new EventHandler<ActionEvent>() {
					    @Override public void handle(ActionEvent e) {
					    	// hard question, so downgrade it...
			        		qe.getCurrentQuestion().resetBox();
			        		
					        if(qe.isOnLast()) {
					        	finishedQuiz();
					        } else {
					        	showCard(qe.getNextQuestion());
					        }
					    }
					});
					
					easyButton.setOnAction(new EventHandler<ActionEvent>() {
					    @Override public void handle(ActionEvent e) {
					    	// easy question, so upgrade it...
			        		qe.getCurrentQuestion().incBox();
			        		
					        if(qe.isOnLast()) {
					        	//message.setText("You have finished the quiz, congratulations!");
					        	finishedQuiz();
					        } else {
					        	showCard(qe.getNextQuestion());
					        }
					    }
					});
					
					controlbox.getChildren().addAll(easyButton, hardButton);
			    }});
				controlbox.getChildren().add(flipButton);
		} else
		if(selectedQuestion.isFill()) {
			/* all cards just display their 'content' (the question part) first */
			for(Multimedia m : selectedQuestion.getContent()) {
				Pane p = FXSlideshowController.vizMultimedia(m);
				
				cardBox.getChildren().add(p);
			}
			
			//HBox for search controls  
			HBox searchBar = new HBox();
			
			//Button for completing search actions 
			Button submit = new Button();
			submit.setText("Submit");
			submit.setMinWidth(100);
			submit.setStyle("-fx-font-size: 15; -fx-text-fill: Black;");
			
			//Create editable ComboBox for queries and history 
			ComboBox<String> searchHistory = new ComboBox<String>();
			searchHistory.setPromptText("Search....");
			searchHistory.setEditable(true);
			searchHistory.setVisibleRowCount(4);
			searchHistory.prefWidthProperty().bind(searchBar.widthProperty());
			searchHistory.setStyle("-fx-font-size: 15; -fx-text-fill: Black;");
			
			//Label for informing user when a query has be auto corrected
			Label search = new Label();
			search.setStyle("-fx-font-size: 15; -fx-text-fill: Black;");
			search.setVisible(false);
			
			//Add nodes to HBox
			searchBar.getChildren().addAll(searchHistory, submit);
			searchBar.setSpacing(10);
			
			//VBox for search controls and user info label
			VBox vb = new VBox();
			vb.getChildren().addAll(searchBar, search);
			
			//Add VBox to top of root borderPane
			rootBorderPane.setBottom(vb);
			
			//When the submit button is clicked
			submit.setOnAction(e->{
				if(selectedQuestion.checkAnswer(searchHistory.getValue().toString())){
					qe.getCurrentQuestion().incBox();
					if(qe.isOnLast()) {
			        	finishedQuiz();
			        } else {
			        		showCard(qe.getNextQuestion());
			        }
				} else {
					qe.getCurrentQuestion().resetBox();
					message.setText("Sorry, that's not correct. Please try again.");
				}
			});
		} else
		if(selectedQuestion.isMultiple()) {
			/* all cards just display their 'content' (the question part) first */
			for(Multimedia m : selectedQuestion.getContent()) {
				System.out.println(i);
				Pane p = FXSlideshowController.vizMultimedia(m);
				
				cardBox.getChildren().add(p);
				i++;
			}
			
			final String alphabet = "abcdefghijklmnopqrstuvwxyz";
			GridPane g = new GridPane();
			g.setPadding(new Insets(100,100,100,100));
			int ansCount = 0;
			
			for(Answer a : selectedQuestion.getAnswers()) {
				VBox ansBox = new VBox();
				for(Multimedia m : a.getMultimedias()) {
					Pane p = FXSlideshowController.vizMultimedia(m);
					ansBox.getChildren().add(p);
				}

				/* create label to associate with multimedia */
				Label ansLetter = new Label();
				ansLetter.setText(Character.toString(Character.toUpperCase(alphabet.charAt(ansCount))));
				
				/* create button */
				Button b = new Button();
				b.setText(Character.toString(Character.toUpperCase(alphabet.charAt(ansCount))));
				if(selectedQuestion.checkAnswer(a.getId())){
					/* set button action for correct answer */
					b.setOnAction(new EventHandler<ActionEvent>() {
					    @Override public void handle(ActionEvent e) {
					    	qe.getCurrentQuestion().incBox();
					        if(qe.isOnLast()) {
					        	finishedQuiz();
					        } else {
					        		showCard(qe.getNextQuestion());
					        }
					    }
					});
				} else {
					b.setOnAction(new EventHandler<ActionEvent>() {
					    @Override public void handle(ActionEvent e) {
					    	qe.getCurrentQuestion().resetBox();
					        message.setText("Sorry, that's not correct. Please try again.");
					    }
					});
				}
				controlbox.getChildren().add(b);
				
				GridPane.setColumnIndex(ansBox, ansCount);
				GridPane.setRowIndex(ansBox, 1);
				GridPane.setColumnIndex(ansLetter, ansCount);
				GridPane.setRowIndex(ansLetter, 0);
				
				g.getChildren().addAll(ansBox, ansLetter);
				ansCount++;
			}
			cardBox.getChildren().add(g);
		}
		
		rootBorderPane.setCenter(cardBox);
	}
}
