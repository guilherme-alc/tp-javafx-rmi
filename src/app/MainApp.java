package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/resources/tela-login.fxml"));
		Scene scene = new Scene(loader.load());
		
		stage.setTitle("Login");
		stage.setScene(scene);
		
		stage.setWidth(558);
		stage.setHeight(458);
		
		stage.setMinWidth(558);
		stage.setMinHeight(458);
		
		stage.setMaxWidth(558);
		stage.setMaxHeight(458);
		
		stage.show();
	}
	
	public static void main(String[] args) { launch(); }

}
