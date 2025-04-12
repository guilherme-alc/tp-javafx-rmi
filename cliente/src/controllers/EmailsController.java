package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private TextArea campoMensagemRecebida;
    @FXML
    private ListView<String> listMensagensRecebidas;
    
    private EmailInterface emailService;
    private Usuario usuario;
    private List<Mensagem> mensagensRecebidas = new ArrayList<>();
    
    public void setEmailService(EmailInterface emailService) {
        this.emailService = emailService;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @FXML
    public void initialize() {

        Thread recarrega = new Thread(new Runnable() {
        	
            @Override
            public void run() {
            	
                try {

                    Thread.sleep(15000); // 15 segundos

                    while (true) {
                    	
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                receberMensagens();
                            }
                        });
                        
                        Thread.sleep(30000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        recarrega.setDaemon(true);
        recarrega.start();

        //seleção de mensagem na lista
        listMensagensRecebidas.setOnMouseClicked(event -> {
            int indice = listMensagensRecebidas.getSelectionModel().getSelectedIndex();
            if (indice >= 0 && indice < mensagensRecebidas.size()) {
                Mensagem mensagem = mensagensRecebidas.get(indice);
                labelRemetente.setText(mensagem.remetente);
                campoMensagemRecebida.setText(mensagem.conteudo);
            }
        });
    }
    
    @FXML
    protected void enviarMensagem() {
    	String remetente = usuario.getEmail();
    	String destinatario = campoDestinatario.getText();
    	String assunto = campoAssunto.getText();
    	String conteudo = campoConteudo.getText();
    	
        if (destinatario.isEmpty() || assunto.isEmpty() || conteudo.isEmpty()) {
        	feedbackEnvio.setText("Preencha todos os campos!");
        	feedbackEnvio.setStyle("-fx-text-fill: red;");
            return;
        }
    	
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
    
    @FXML
    protected void receberMensagens() {
    	try {
    		mensagensRecebidas = emailService.receberNotificacao(usuario.email);
    		List<String> assuntos = new ArrayList<>();
    		
    		for(Mensagem mensagem : mensagensRecebidas) {
    			assuntos.add(mensagem.assunto);
    		}
    		
    		ObservableList<String> itens = FXCollections.observableArrayList(assuntos);
    		listMensagensRecebidas.setItems(itens);
    		
    		
    	} catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @FXML
    protected void encerrarAplicacao() {
    	Platform.exit();
    }
    
    @FXML
    protected void fazerLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tela-login.fxml"));
            Parent root = loader.load();
            
            LoginController controller = loader.getController();
            controller.setEmailService(emailService);

            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            Stage telaEmail = (Stage) campoAssunto.getScene().getWindow();
            telaEmail.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
