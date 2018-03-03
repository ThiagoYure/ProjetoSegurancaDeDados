package criptografia;

import java.security.MessageDigest;

public class ExemploHash {

    public ExemploHash() {
    }

    /**
     * Método para gerar valores de HASH para string/texto
     *
     * @param mensagem
     * @param algoritmo
     * @return
     * @throws Exception
     */
    public byte[] gerarHashString(byte[] mensagem, String algoritmo) throws Exception {

        MessageDigest digest = MessageDigest.getInstance(algoritmo);

        byte[] resultado = digest.digest(mensagem);

        return resultado;
    }

    /**
     * Método auxiliar de conversão de array de bytes para uma string
     * hexadecimal
     *
     * @param bytes
     * @return
     */
    public String byteToString(byte[] bytes) {
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            resultado.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return resultado.toString();
    }

    /**
     * Método para gerar valores de HASH para arquivos binários Faz a leitura em
     * lotes de bytes e gera o hash em cada um até completar toda a iteração do
     * arquivo
     *
     * @param file
     * @param algoritmo
     * @return
     * @throws Exception
     */
}
