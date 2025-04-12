package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

//interface do servidor
public interface EmailInterface extends Remote {
	public char cadastroEmail(String nome, String email, String senha) throws RemoteException;
	
	public Usuario fazerLogin(String email, String senha) throws RemoteException;

	public boolean enviarMensagem(String remetente, String destinatario,  String assunto, String conteudo) throws RemoteException;

	public void receberNotificacao(String email, String conteudo , String assunto) throws RemoteException;
}
