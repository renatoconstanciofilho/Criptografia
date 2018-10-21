package sample;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class EncriptaDescriptaAES {
    static String IV = "AAAAAAAAAAAAAAAA";

    public EncriptaDescriptaAES() {
    }


//    static String textopuro = "teste texto 12345678\0\0\0";
//    static String chaveencriptacao = "0123456789abcdef";

//    public static void main(String[] args) {
//
//        try {
//
//            System.out.println("Texto Puro: " + textopuro);
//
//            byte[] textoencriptado = encrypt(textopuro, chaveencriptacao);
//
//            System.out.print("Texto Encriptado: ");
//
//            for (int i = 0; i < textoencriptado.length; i++)
//                System.out.print(new Integer(textoencriptado[i]) + " ");
//
//            System.out.println("");
//
//            String textodecriptado = decrypt(textoencriptado, chaveencriptacao);
//
//            System.out.println("Texto Decriptado: " + textodecriptado);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public String encrypt(String text, String encryptionKey) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(text.getBytes("UTF-8")).toString();
    }

    public String decrypt(String et, String encryptionKey) throws Exception {
        byte[] encryptedText = et.getBytes(StandardCharsets.UTF_8);
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(decripta.doFinal(encryptedText), "UTF-8");
    }

}