package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//usuário servidor
//o serializable foi necessário para poder enviar o objeto do cliente/servidor e vice-versa
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
        mensagens = new ArrayList<>();
    }

    public Usuario() { }

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
