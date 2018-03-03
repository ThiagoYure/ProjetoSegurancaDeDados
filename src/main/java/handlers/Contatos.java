package handlers;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import modelo.Usuario;
import modelo.UsuarioDao;

/**
 *
 * @author Ricarte
 */
public class Contatos extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException {
        UsuarioDao dao = new UsuarioDao();
        getJspContext().setAttribute("Contatos", dao.readUsuarios()); 
    }
    
}
