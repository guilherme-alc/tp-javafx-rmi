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

import java.io.IOException;

public class CadastroController {
    @FXML
    private TextField campoNome;
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
    private void cadastrarUsuario() {
        String nome = campoNome.getText();
        String email = campoEmail.getText().trim().toLowerCase();
        String senha = campoSenha.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            labelFeedback.setText("Preencha todos os campos!");
            labelFeedback.setStyle("-fx-text-fill: red;");
            return;
        }
        	try {
            	char resultado = emailService.cadastroEmail(nome, email, senha);
            	
            	if(resultado == 's') {
                    labelFeedback.setText("Usuário cadastrado com sucesso!");
                    labelFeedback.setStyle("-fx-text-fill: green;");
                    campoNome.clear();
                    campoEmail.clear();
                    campoSenha.clear();
                    return;
            	}
            	else if(resultado == 'j') {
                    labelFeedback.setText("Já existe um usuário cadastrado com esse e-mail!");
                    labelFeedback.setStyle("-fx-text-fill: red;");
                    return;
            	}
            	else if(resultado == 'e') {
                    labelFeedback.setText("Erro ao salvar o usuário.");
                    labelFeedback.setStyle("-fx-text-fill: red;");
                    return;
            	}
        	} catch (Exception e) {
            e.printStackTrace();
            labelFeedback.setText("Erro ao conectar no servidor.");
            labelFeedback.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    protected void abrirTelaLogin(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tela-login.fxml"));
            Parent root = loader.load();

            LoginController controller = loader.getController();
            controller.setEmailService(emailService);
            
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            Stage telaCadastro = (Stage) campoEmail.getScene().getWindow();
            telaCadastro.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}