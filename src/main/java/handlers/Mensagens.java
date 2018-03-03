/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import modelo.Mensagem;
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
     */
    @Override
    public void doTag() throws JspException {
        UsuarioDao dao = new UsuarioDao();
        getJspContext().setAttribute("Mensagens", dao.readMensagens(usuario, destinatario));        
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    
}
