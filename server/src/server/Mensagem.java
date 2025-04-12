package server;

import java.io.Serializable;

//mensagem servidor
public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String remetente;
	public String destinatario;
	public String assunto;
	public String conteudo;
	
    public Mensagem(String remetente, String destinatario, String assunto, String conteudo) {
        this.remetente = remetente;
    	this.destinatario = destinatario;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }
    
    public String getRemetente() {
        return remetente;
    }
    
    public String getDestinatario() {
        return destinatario;
    }

    public String getAssunto(){
        return assunto;
    }

    public String getConteudo(){
        return  conteudo;
    }
}
