package criptografia;


/**
 * Criptografia com o algoritmo AES.
 *
 * O objetivo é a criação de instâncias de algoritmos de criptografia
 * para criptografar um arquivo de texto.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ExemploAES {

    // definindo o algoritmo de criptgrafia (simétrico) com AES
    private static final String ALGORITHM = "AES";

    // chave de criptografia (precisa estar segura!) 16 caracteres
    private final byte[] key = "MZygpewJsCpRrfOr".getBytes(StandardCharsets.UTF_8);

    /**
     * Criptografar o arquivo com o algoritmo AES.
     *
     */
    public ExemploAES() {
    }

    public byte[] criptografarArquivo(byte[] conteudo) throws Exception {

        // Inicia o objeto que guarda as chaves para o algoritmo
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);

        // Objeto de cifra, com AES
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Inicia o processo
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Finaliza (doFinal é obrigatório)
        byte[] result = cipher.doFinal(conteudo);

        return result;
    }

    /**
     *
     * @param conteudo
     * @return
     * @throws Exception
     */
    public byte[] descriptografarArquivo(byte[] conteudo) throws Exception {

        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] result = cipher.doFinal(conteudo);

        return result;

    }

    /**
     * Outros exemplos relevantes:
     * http://commons.apache.org/proper/commons-crypto/xref-test/org/apache/commons/crypto/examples/CipherByteArrayExample.html
     * http://commons.apache.org/proper/commons-crypto/xref-test/org/apache/commons/crypto/examples/CipherByteBufferExample.html
     * https://msdn.microsoft.com/pt-br/library/te15te69(v=vs.110).aspx
     *
     * @param args
     * @throws Exception
     */
}
