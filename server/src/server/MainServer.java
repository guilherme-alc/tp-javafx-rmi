package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {
	public MainServer() {
        System.setProperty("java.rmi.server.hostname", "192.168.1.9");
        try {
            System.out.println("Tentando inicializar o servidor...");
            
            EmailInterface c = new EmailImpl();
            
            Registry reg = LocateRegistry.createRegistry(1098);
            reg.rebind("EmailService", c);
            
            System.out.println("Servidor online!");
            
            // tive que usar para o servidor manter rodando
            while (true) {               
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println("Erro ao instanciar servidor: " + e);
            e.printStackTrace();
        }
	}


	public static void main(String args[]) {
		new MainServer();
	}
}
