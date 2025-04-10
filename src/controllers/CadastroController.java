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
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CadastroController {
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoSenha;

    private static final String CAMINHO_ARQUIVO = "usuarios.json";

    @FXML
    private Label labelFeedback;

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

        Usuario novoUsuario = new Usuario(nome, email, senha);

        try {
            List<Usuario> usuarios = new ArrayList<>();
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

            try (Reader reader = new FileReader(CAMINHO_ARQUIVO)) {
                usuarios = gson.fromJson(reader, tipoLista);
                if (usuarios == null) usuarios = new ArrayList<>();
            } catch (Exception e) {

            }

            boolean emailExiste = usuarios.stream()
                    .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));

            if (emailExiste) {
                labelFeedback.setText("Já existe um usuário cadastrado com esse e-mail!");
                labelFeedback.setStyle("-fx-text-fill: red;");
                return;
            }

            usuarios.add(novoUsuario);

            try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
                gson.toJson(usuarios, writer);
            }

            labelFeedback.setText("Usuário cadastrado com sucesso!");
            labelFeedback.setStyle("-fx-text-fill: green;");
            campoNome.clear();
            campoEmail.clear();
            campoSenha.clear();

        } catch (Exception e) {
            e.printStackTrace();
            labelFeedback.setText("Erro ao salvar o usuário.");
            labelFeedback.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    protected void abrirTelaLogin(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tela-login.fxml"));
            Parent root = loader.load();

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