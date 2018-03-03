/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contole;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Command;
import modelo.Usuario;
import modelo.UsuarioDao;

/**
 *
 * @author Ricarte
 */
class EnviarMensagemController implements Command{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ClassNotFoundException, IOException, ServletException {
        String texto = req.getParameter("texto");
        String destinatario = req.getParameter("destinatario");
        HttpSession s = req.getSession();
        String remetente = ((Usuario) s.getAttribute("user")).getEmail();       
        UsuarioDao dao = new UsuarioDao();
        try {
            dao.createMessagem(destinatario, remetente, texto);
            res.sendRedirect("conversa.jsp?emailCont="+destinatario);
        } catch (Exception ex) {
            Logger.getLogger(EnviarMensagemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
