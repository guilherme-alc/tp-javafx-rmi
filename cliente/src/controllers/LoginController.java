package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import server.EmailInterface;
import server.Usuario;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField campoEmail;
    @FXML
    private TextField campoSenha;
    @FXML
    private Label labelFeedback;

    
    private EmailInterface emailService;
    
    public void setEmailService(EmailInterface emailService) {
        this.emailService = emailService;
    }

    @FXML
    protected void fazerLogin() {
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            labelFeedback.setText("Preencha todos os campos!");
            labelFeedback.setStyle("-fx-text-fill: red;");
            return;
        }
        
        try {
        	Usuario usuario = emailService.fazerLogin(email, senha);
        	if(usuario == null) {
                labelFeedback.setText("Usuário não cadastrado, ou login está incorreto");
                labelFeedback.setStyle("-fx-text-fill: red;");
                return;
    		}
        	
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tela-emails.fxml"));
            Parent root = loader.load();
            
            EmailsController controller = loader.getController();
            controller.setEmailService(emailService);
            controller.setUsuario(usuario);
            controller.receberMensagens();

            Stage stage = new Stage();
            stage.setTitle("Caixa de Entrada");

            stage.setWidth(788);
            stage.setHeight(587);

            stage.setMinWidth(788);
            stage.setMinHeight(587);

            stage.setMaxWidth(788);
            stage.setMaxHeight(587);

            stage.setScene(new Scene(root));
            stage.show();

            Stage telaLogin = (Stage) campoEmail.getScene().getWindow();
            telaLogin.close();

    	}catch(IOException ex) {
        	ex.printStackTrace();
        }     
    }

    @FXML
    protected void abrirTelaCadastro(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tela-cadastro.fxml"));
            Parent root = loader.load();
            
            CadastroController controller = loader.getController();
            controller.setEmailService(emailService);

            Stage stage = new Stage();
            stage.setTitle("Cadastro");
            stage.setScene(new Scene(root));
            stage.show();

            Stage telaLogin = (Stage) campoEmail.getScene().getWindow();
            telaLogin.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
