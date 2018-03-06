package Handlers;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import modelo.UsuarioDao;

/**
 *
 * @author Ricarte
 */
public class Contatos extends SimpleTagSupport {
    private String email;
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException {
        UsuarioDao dao = new UsuarioDao();
        getJspContext().setAttribute("Contatos", dao.readUsuarios(email)); 
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
