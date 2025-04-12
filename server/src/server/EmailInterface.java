package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

//interface do servidor
public interface EmailInterface extends Remote {
	public char cadastroEmail(String nome, String email, String senha) throws RemoteException;
	
	public Usuario fazerLogin(String email, String senha) throws RemoteException;

	public boolean enviarMensagem(String remetente, String destinatario,  String assunto, String conteudo) throws RemoteException;

	public List<Mensagem> receberNotificacao(String email) throws RemoteException;
}
