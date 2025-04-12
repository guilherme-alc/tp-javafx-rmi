package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.EmailInterface;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import controllers.LoginController;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		try {
			try {
				System.out.println("Tentando conectar ao servidor...");
				EmailInterface c = (EmailInterface) Naming.lookup("rmi://192.168.1.9:1098/EmailService");
				System.out.println("Conectado com sucesso!");
				
				FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/views/tela-login.fxml"));
				Scene scene = new Scene(loader.load());
				
				LoginController controller = loader.getController();
				controller.setEmailService(c);
				
				stage.setTitle("Login");
				stage.setScene(scene);
				
				stage.setWidth(558);
				stage.setHeight(458);
				
				stage.setMinWidth(558);
				stage.setMinHeight(458);
				
				stage.setMaxWidth(558);
				stage.setMaxHeight(458);
				
				stage.show();
			} catch (MalformedURLException ex) {
				System.out.println(ex);
			} catch (NotBoundException ex) {
				System.out.println(ex);
			}
		} catch(RemoteException ex) {
			System.out.println(ex);
		}
	}
	
	public static void main(String[] args) { launch(); }

}
