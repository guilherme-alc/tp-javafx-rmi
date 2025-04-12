package server;

import java.io.Serializable;

//mensagem cliente
public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String email;
	public String assunto;
	public String conteudo;
	
    public Mensagem(String email, String assunto, String conteudo) {
        this.email = email;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }
    
    public String getEmail() {
        return email;
    }

    public String getAssunto(){
        return assunto;
    }

    public String getConteudo(){
        return  conteudo;
    }
}
