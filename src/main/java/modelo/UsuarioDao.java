/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import criptografia.ExemploAES;
import criptografia.ExemploRSA;
import factory.ConFactory;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThigoYure
 */
public class UsuarioDao {

    public boolean create(Usuario novo) {
        try {
            int retorno;
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("INSERT INTO usuario (email,senha,nick) VALUES(?,?,?)");
                st.setString(3, novo.getNick());
                st.setString(1, novo.getEmail());
                st.setBytes(2, novo.getSenha());
                retorno = st.executeUpdate();
                st.close();
            }
            if (retorno > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Usuario read(String email) {

        try {
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("SELECT * FROM usuario WHERE email=?");
                st.setString(1, email);
                ResultSet r = st.executeQuery();
                if (r.next()) {
                    Usuario user = new Usuario();
                    user.setEmail(r.getString("email"));
                    user.setSenha(r.getBytes("senha"));
                    user.setNick(r.getString("nick"));
                    st.close();
                    con.close();
                    return user;
                }
                st.close();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Usuario> readUsuarios(String usuario) {

        try {
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("SELECT * FROM usuario where not email = ?");
                st.setString(1,usuario );
                ResultSet r = st.executeQuery();
                ArrayList<Usuario> resultado = new ArrayList<>();
                while (r.next()) {
                    Usuario user = new Usuario();
                    user.setEmail(r.getString("email"));
                    user.setNick(r.getString("nick"));
                    user.setSenha(r.getBytes("senha"));
                    resultado.add(user);
                }
                con.close();
                st.close();
                return resultado;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean createMessagem(String destinatario, String remetente, String texto) throws Exception {
        try {
            int retorno;
            ExemploRSA rsa = new ExemploRSA();
            ExemploAES aes = new ExemploAES();
            byte[] chavecriptada = readpk(destinatario);
            byte[] publicKeyBytes = aes.descriptografarArquivo(chavecriptada);
            byte[] chavecriptadauser = readpk(remetente);
            byte[] publicKeyBytesuser = aes.descriptografarArquivo(chavecriptadauser);
            KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
            //PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
            PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            PublicKey publicKeyuser = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytesuser));
            try (Connection con = ConFactory.getConnection(); PreparedStatement st = con.prepareStatement("INSERT INTO mensagem (destinatario,remetente,"
                    + "texto) VALUES(?,?,?)")) {
                st.setString(1, destinatario);
                st.setString(2, remetente);
                byte[] textoCrip = rsa.criptografarMensagem(texto.getBytes(), publicKey);
                st.setBytes(3, textoCrip);
                retorno = st.executeUpdate();
            }
            try (Connection con = ConFactory.getConnection(); PreparedStatement st = con.prepareStatement("INSERT INTO mensagem (destinatario,remetente,"
                    + "texto) VALUES(?,?,?)")) {
                st.setString(1, destinatario);
                st.setString(2, remetente);
                byte[] textoCrip = rsa.criptografarMensagem(texto.getBytes(), publicKeyuser);
                st.setBytes(3, textoCrip);
                retorno = st.executeUpdate();
            }
            return retorno > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList<Mensagem> readMensagens(String usuario, String contato) throws Exception {

        try {
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("SELECT * FROM mensagem WHERE (destinatario = ? or remetente = ?)and(remetente = ? or destinatario = ?) order by id asc");
                st.setString(1, usuario);
                st.setString(2, usuario);
                st.setString(3, contato);
                st.setString(4, contato);
                ExemploAES aes = new ExemploAES();
                ArrayList<Mensagem> resultado = new ArrayList<>();
                ResultSet r = st.executeQuery();
                ExemploRSA rsa = new ExemploRSA();
                byte[] chavecriptada = readprivk(usuario);
                byte[] privateKeyBytes = aes.descriptografarArquivo(chavecriptada);
                KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
                PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
                //PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
                while (r.next()) {
                    Mensagem msg = new Mensagem();
                    msg.setId(r.getInt("id"));
                    msg.setDestinatario(r.getString("destinatario"));
                    msg.setRemetente(r.getString("remetente"));
                    byte[] textoCrip = r.getBytes("texto");
                    try {
                        byte[] textoDescrip = rsa.descriptografarMensagem(textoCrip, privateKey);
                        msg.setTexto(new String(textoDescrip));
                        resultado.add(msg);
                    } catch (Exception e) {
                    }

                }
                con.close();
                st.close();
                return resultado;

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean createPK(byte[] pk, String email) {
        try {
            int retorno;
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("INSERT INTO pkusers (key_pub,usuario) VALUES(?,?)");
                st.setBytes(1, pk);
                st.setString(2, email);
                retorno = st.executeUpdate();
                st.close();
            }
            if (retorno > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean createPrivKey(byte[] privk, String email) {
        System.out.println("oi");
        try {
            int retorno;
            try (Connection con = ConFactory.getConnection(); PreparedStatement st = con.prepareStatement("INSERT INTO privkusers (key_priv,usuario) VALUES(?,?)")) {
                st.setBytes(1, privk);
                st.setString(2, email);
                retorno = st.executeUpdate();
            }
            return retorno > 0;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public byte[] readpk(String email) {

        try {
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("SELECT * FROM pkusers WHERE usuario = ?");
                st.setString(1, email);
                ResultSet r = st.executeQuery();
                if (r.next()) {
                    byte[] retorno = r.getBytes("key_pub");
                    con.close();
                    st.close();
                    return retorno;
                }
                return null;

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public byte[] readprivk(String email) {

        try {
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("SELECT * FROM privkusers WHERE usuario = ?");
                st.setString(1, email);
                ResultSet r = st.executeQuery();
                if (r.next()) {
                    byte[] retorno = r.getBytes("key_priv");
                    System.out.println(Arrays.toString(retorno));
                    con.close();
                    st.close();
                    return retorno;
                }
                return null;

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
