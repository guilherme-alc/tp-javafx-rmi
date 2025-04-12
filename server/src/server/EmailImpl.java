package server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class EmailImpl extends UnicastRemoteObject implements EmailInterface {
    private static final long serialVersionUID = 1L;
    
	protected EmailImpl() throws RemoteException {
		super();
	}

	private static final String CAMINHO_ARQUIVO = "usuarios.json";
	
	@Override
	public char cadastroEmail(String nome, String email, String senha) throws RemoteException {
		Usuario novoUsuario = new Usuario(nome, email, senha);
		try {
				List<Usuario> usuarios = new ArrayList<>();
				Gson gson = new Gson();
				Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
				
				try (Reader reader = new FileReader(CAMINHO_ARQUIVO)) {
				    usuarios = gson.fromJson(reader, tipoLista);
				    
				    if (usuarios == null) {
				    	usuarios = new ArrayList<>();
				    }
				} catch (Exception e) {
					return 'e';
				}
				
				boolean emailExiste = usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
				
				if (emailExiste) {
				    return 'j';
				}
				
				usuarios.add(novoUsuario);
				
				try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
				    gson.toJson(usuarios, writer);
				}
				
				return 's';
	
        } catch (Exception e) {
            e.printStackTrace();
            return 'e';
        }
	}
	
	@Override
	public Usuario fazerLogin(String email, String senha) throws RemoteException {
		Gson gson = new Gson();
        Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

        try (Reader leitor = new FileReader("usuarios.json")) {
            List<Usuario> usuarios = gson.fromJson(leitor, tipoLista);
            
            if (usuarios == null) {
            	return null;
            }

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

	@Override
	public boolean enviarMensagem(String remetente, String destinatario, String assunto, String conteudo) throws RemoteException {
		Mensagem mensagem = new Mensagem (remetente, destinatario, assunto, conteudo);
		
		Gson gson = new Gson();
        Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

        try (Reader leitor = new FileReader("usuarios.json")) {
            List<Usuario> usuarios = gson.fromJson(leitor, tipoLista);
            
            if (usuarios == null) {
            	return false;
            }
            
            boolean destinatarioExiste = false;

            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equalsIgnoreCase(destinatario.trim())) {
                	
                    if (usuario.mensagens == null) {
                        usuario.mensagens = new ArrayList<>();
                    }
                    
                    usuario.mensagens.add(mensagem);
                    destinatarioExiste = true;
                    break;	
                }
            }
            
            if(destinatarioExiste) {
				try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
				    gson.toJson(usuarios, writer);
				    return true;
				    
				}catch(Exception ex) {
					ex.printStackTrace();
					return false;
				}
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return false;
	}

	@Override
	public void receberNotificacao(String email, String conteudo, String assunto) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
