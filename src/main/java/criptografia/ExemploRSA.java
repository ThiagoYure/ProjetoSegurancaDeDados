package criptografia;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;

public class ExemploRSA {

    public ExemploRSA() {
    }

    /**
     * Gerando as chaves pública e privada Método RSA
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public KeyPair geradorChaves() throws NoSuchAlgorithmException {

        // chaves de tamanho 2048 (padrão)
        final int lenChave = 2048;
        // criando uma instância do gerador de chaves com o RSA
        KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
        // inicializando com o tamanho de chave definido
        gerador.initialize(lenChave);

        // cria as chaves pública e privada
        return gerador.genKeyPair();
    }

    /**
     * Método para criptografar a mensagem Exemplo utilizado: criptografia feita
     * com a chave pública
     *
     * @param mensagem
     * @param chavePublica
     * @return
     * @throws Exception
     */
    public byte[] criptografarMensagem(byte[] mensagem, PublicKey chavePublica) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, chavePublica);

        return cipher.doFinal(mensagem);
    }

    /**
     * Método para descriptografar uma mensagem Exemplo utilizado:
     * descriptografia feita com chave privada
     *
     * @param mensagem
     * @param chavePrivada
     * @return
     * @throws Exception
     */
    public byte[] descriptografarMensagem(byte[] mensagem, PrivateKey chavePrivada) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
        return cipher.doFinal(mensagem);
    }

}
