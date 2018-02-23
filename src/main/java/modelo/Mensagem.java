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
public class Mensagem {
    private int id;
    private byte[] texto;
    private String remetente;
    private String destinatario;

    public Mensagem() {
    }

    public Mensagem(int id, byte[] texto, String remetente, String destinatario) {
        this.id = id;
        this.texto = texto;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getTexto() {
        return texto;
    }

    public void setTexto(byte[] texto) {
        this.texto = texto;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Arrays.hashCode(this.texto);
        hash = 59 * hash + Objects.hashCode(this.remetente);
        hash = 59 * hash + Objects.hashCode(this.destinatario);
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
        final Mensagem other = (Mensagem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.remetente, other.remetente)) {
            return false;
        }
        if (!Objects.equals(this.destinatario, other.destinatario)) {
            return false;
        }
        if (!Arrays.equals(this.texto, other.texto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", texto=" + texto + ", remetente=" + remetente + ", destinatario=" + destinatario + '}';
    }

    
    
}
