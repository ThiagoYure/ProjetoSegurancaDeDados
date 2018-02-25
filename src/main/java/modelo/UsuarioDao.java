/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import factory.ConFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public ArrayList<Usuario> readUsuarios() {

        try {
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("SELECT * FROM usuario");
                ResultSet r = st.executeQuery();
                ArrayList<Usuario> resultado = new ArrayList<>();
                Usuario user = new Usuario();
                while (r.next()) {
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

    public boolean createMessagem(String destinatario, String remetente, byte[] texto) {
        try {
            int retorno;
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("INSERT INTO mensagem (destinatario,remetente,"
                        + "texto) VALUES(?,?,?)");
                st.setString(1, destinatario);
                st.setString(2, remetente);
                st.setBytes(3, texto);
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

    public ArrayList<Mensagem> readMensagens(String email) {

        try {
            try (Connection con = ConFactory.getConnection()) {
                PreparedStatement st = con.prepareStatement("SELECT * FROM mensagem WHERE destinatario=? OR remetente=? limit 10 order by[desc]");
                st.setString(1, email);
                ArrayList<Mensagem> resultado = new ArrayList<>();
                ResultSet r = st.executeQuery();
                while (r.next()) {
                    Mensagem msg = new Mensagem();
                    msg.setId(r.getInt("id"));
                    msg.setDestinatario(r.getString("destinatario"));
                    msg.setRemetente(r.getString("remetente"));
                    msg.setTexto(r.getBytes("mensagem"));
                    resultado.add(msg);
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
                PreparedStatement st = con.prepareStatement("INSERT INTO pkUsers (pk,user) VALUES(?,?)");
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
}
