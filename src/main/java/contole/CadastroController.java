/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contole;

import criptografia.ExemploHash;
import criptografia.ExemploRSA;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Command;
import modelo.Usuario;
import modelo.UsuarioDao;

/**
 *
 * @author ThigoYure
 */
class CadastroController implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ClassNotFoundException, IOException, ServletException {
        ExemploHash hash = new ExemploHash();
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        byte[] senhaCriptografada;
        ExemploRSA exemplo = new ExemploRSA();
        try {
            KeyPair chaves = exemplo.geradorChaves();
            PublicKey chavePublica = chaves.getPublic();
            PrivateKey chavePrivada = chaves.getPrivate();
            senhaCriptografada = hash.gerarHashString(senha.getBytes(), "MD5");
            String nick = req.getParameter("nick");
            Usuario novoUser = new Usuario(email, senhaCriptografada, nick);
            UsuarioDao dao = new UsuarioDao();
            if (email.equals("") || nick.equals("") || senha.equals("")) {
                res.sendRedirect("cadastro.jsp?msg='Campos vazios...'");
            } else {
                if (dao.read(email) == null) {
                    if (dao.create(novoUser)) {
                        dao.createPK(hash.gerarHashString(chavePublica.getEncoded(),"MD5"), email);
                        dao.createPrivKey(hash.gerarHashString(chavePrivada.getEncoded(),"MD5"), email);
                        res.sendRedirect("index.jsp");
                    } else {
                        res.sendRedirect("cadastro.jsp?msg='Falha ao criar nova conta...Recarregue a página e tente novamente.'");
                    }
                } else {
                    res.sendRedirect("cadastro.jsp?msg='Já existe um usuario vinculado a este email...'");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
