package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import server.EmailInterface;
import server.Mensagem;
import server.Usuario;

public class EmailsController {
    @FXML
    private TextField campoAssunto;
    @FXML
    private TextField campoDestinatario;
    @FXML
    private TextArea campoConteudo;
    
    @FXML
    private Label feedbackEnvio;
    
    @FXML
    private Label labelRemetente;
    @FXML
    private ListView listMensagensRecebidas;
    
    private EmailInterface emailService;
    private Usuario usuario;
    
    public void setEmailService(EmailInterface emailService) {
        this.emailService = emailService;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @FXML
    protected void enviarMensagem() {
    	String remetente = usuario.getEmail();
    	String destinatario = campoDestinatario.getText();
    	String assunto = campoAssunto.getText();
    	String conteudo = campoConteudo.getText();
    	
    	try {
    		boolean sucesso = emailService.enviarMensagem(remetente, destinatario, assunto, conteudo);
    		
    		if(!sucesso) {
    			feedbackEnvio.setText("Destinatário não encontrado, ou e-mail está incorreto.");
    			feedbackEnvio.setStyle("-fx-text-fill: red;");
    			return;
    		}
			feedbackEnvio.setText("Mensagem enviado com sucesso!");
			feedbackEnvio.setStyle("-fx-text-fill: green;");
			
			campoAssunto.clear();
			campoDestinatario.clear();
			campoConteudo.clear();
			
    		
    	}catch(IOException ex) {
        	ex.printStackTrace();
        }  
    }
}
