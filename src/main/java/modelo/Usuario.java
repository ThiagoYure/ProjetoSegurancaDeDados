/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author ThigoYure
 */
public class Usuario {
    private String email;
    private byte[] senha;
    private String nick;

    public Usuario(String email, byte[] senha, String nick) {
        this.email = email;
        this.senha = senha;
        this.nick = nick;
    }

    Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSenha() {
        return senha;
    }

    public void setSenha(byte[] senha) {
        this.senha = senha;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Arrays.hashCode(this.senha);
        hash = 71 * hash + Objects.hashCode(this.nick);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.nick, other.nick)) {
            return false;
        }
        if (!Arrays.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", senha=" + senha + ", nick=" + nick + '}';
    }
    
    
    
}
