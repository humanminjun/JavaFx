package application;
	

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label; //레이블 import할때 javafx로함!
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			//stage > scene > container > node
			
															//node는 어디감??
			//Pane pane = new Pane(); //pane 이라는 container
	  StackPane pane = new StackPane(); //container종류 중 하나
					
			Scene scene = new Scene(pane,300,500);
			
			stage.setScene(scene);
			
			stage.show();
			
			stage.setTitle("add program");
			stage.setResizable(false);

			stage.setTitle("My window"); // 타이틀 삽입
			stage.setResizable(false); //크기 조절 금지
			
			//글씨 삽입하기~ label import 할때 유의
			Label text = new Label();     //레이블
			text.setText("hello javaFx"); //출력하기~~
			pane.getChildren().add(text); //출력하기~~??????????
			//CSS 폰트사이즈 및 글자색,배경색 조정 나중에는 외부파일로 처리해야함
			text.setStyle("-fx-font-size:50; -fx-text-fill:red; -fx-background-color:yellow");
			
			StackPane.setAlignment(text, Pos.BOTTOM_CENTER);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
