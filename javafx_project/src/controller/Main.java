package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Word;
import model.WordDAO;

public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	public static ObservableList<Word> wordList = FXCollections.observableArrayList();

	public Main() {
	  wordList.add(new Word("사과", "Apple"));
	  wordList.add(new Word("바나나", "Banan"));
	  wordList.add(new Word("낙타", "Camel"));
	}

	public ObservableList<Word> getWordList() {
	  return wordList;
	}
	

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("영한 사전 프로그램");
		setRootLayout();
		setWordMainView();
	}	

		
	public void setRootLayout() {
		try {	
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		public void setWordMainView() {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/WordMainView.fxml"));
				AnchorPane wordMainView = (AnchorPane) loader.load();
				rootLayout.setCenter(wordMainView);
				
				WordMainController controller = loader.getController();
				controller.setMain(this);
				
			WordDAO wordDAO = new WordDAO();
			ObservableList<Word> tempList = wordDAO.getWordList();
				for(int i = 0; i < tempList.size(); i++) {
					wordList.add(tempList.get(i));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public Stage getPrimaryStage() {
			return primaryStage;
		}
		
		public static void main(String[]args) {
			launch(args);
		}

		public int setWordDataView(Word word) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/WordDataView.fxml"));
				AnchorPane page = (AnchorPane)loader.load();
				
				Stage dialogStage = new Stage();
				dialogStage.setTitle("단어장 관리");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.initOwner(primaryStage);
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				
				WordDataController controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setWord(word);
				
				dialogStage.showAndWait();
				return controller.getReturnValue();
			}catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
		
	
		@FXML
		private void saveAction() {
			WordDAO wordDAO = new WordDAO();
			int result = wordDAO.saveWordList(wordList);
			if(result == 1) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initOwner(primaryStage);
				alert.setTitle("성공메시지");
				alert.setHeaderText("성공적으로 수행했습니다.");
				alert.setContentText("데이터베이스에 성공적으로 접근했습니다.");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(primaryStage);
				alert.setTitle("오류메시지");
				alert.setHeaderText("오류가 발생했습니다.");
				alert.setContentText("데이터베이스에 오류가 발생했습니다.");
				alert.showAndWait();
			}
			
		}
		@FXML
		private void exitAction() {
			System.exit(1);
		}
		@FXML
		private void aboutAction() {
		}
	}
		
	
		