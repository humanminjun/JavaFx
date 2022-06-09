package application;
	

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label; //���̺� import�Ҷ� javafx����!
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			//stage > scene > container > node
			
															//node�� ���??
			//Pane pane = new Pane(); //pane �̶�� container
	  StackPane pane = new StackPane(); //container���� �� �ϳ�
					
			Scene scene = new Scene(pane,300,500);
			
			stage.setScene(scene);
			
			stage.show();
			
			stage.setTitle("add program");
			stage.setResizable(false);

			stage.setTitle("My window"); // Ÿ��Ʋ ����
			stage.setResizable(false); //ũ�� ���� ����
			
			//�۾� �����ϱ�~ label import �Ҷ� ����
			Label text = new Label();     //���̺�
			text.setText("hello javaFx"); //����ϱ�~~
			pane.getChildren().add(text); //����ϱ�~~??????????
			//CSS ��Ʈ������ �� ���ڻ�,���� ���� ���߿��� �ܺ����Ϸ� ó���ؾ���
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