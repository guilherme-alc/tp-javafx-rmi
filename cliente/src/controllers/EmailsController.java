package controllers;

import server.EmailInterface;
import server.Usuario;

public class EmailsController {
    private EmailInterface emailService;
    private Usuario usuario;
    
    public void setEmailService(EmailInterface emailService) {
        this.emailService = emailService;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
