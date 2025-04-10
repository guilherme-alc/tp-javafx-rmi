package controllers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class LoginController {
    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoSenha;

    @FXML
    private Label labelFeedback;

    private static final String CAMINHO_ARQUIVO = "usuarios.json";

    @FXML
    protected void fazerLogin() {
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            labelFeedback.setText("Preencha todos os campos!");
            labelFeedback.setStyle("-fx-text-fill: red;");
            return;
        }

        Gson gson = new Gson();
        Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

        Usuario usuario = obterUsuario(email, senha);
        if(usuario == null) {
            labelFeedback.setText("Usuário não cadastrado, ou login está incorreto");
            labelFeedback.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/tela-emails.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Caixa de Entrada");

            stage.setWidth(558);
            stage.setHeight(458);

            stage.setMinWidth(558);
            stage.setMinHeight(458);

            stage.setMaxWidth(558);
            stage.setMaxHeight(458);

            stage.setScene(new Scene(root));
            stage.show();

            Stage telaLogin = (Stage) campoEmail.getScene().getWindow();
            telaLogin.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Usuario obterUsuario(String email, String senha) {
        Gson gson = new Gson();
        Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

        try (Reader leitor = new FileReader("usuarios.json")) {
            List<Usuario> usuarios = gson.fromJson(leitor, tipoLista);
            if (usuarios == null) return null;

            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equalsIgnoreCase(email.trim()) && usuario.getSenha().equals(senha)) {
                    return usuario;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @FXML
    protected void abrirTelaCadastro(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/tela-cadastro.fxml"));
            Parent root = loader.load();

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
