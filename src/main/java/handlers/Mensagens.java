/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import modelo.UsuarioDao;

/**
 *
 * @author Ricarte
 */
public class Mensagens extends SimpleTagSupport {

    private String usuario;
    private String destinatario;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException {
        UsuarioDao dao = new UsuarioDao();
        try {        
            getJspContext().setAttribute("Mensagens", dao.readMensagens(usuario, destinatario));
        } catch (Exception ex) {
            Logger.getLogger(Mensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    
}
