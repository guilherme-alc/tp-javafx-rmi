package server;

import java.io.Serializable;
import java.util.List;

//usuário cliente
public class Usuario implements Serializable {
  private static final long serialVersionUID = 1L;
  
    public String nome;
    public String email;
    public String senha;
    public List<Mensagem> mensagens;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return  senha;
    }
}
