package com.pierre.despeze.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryption {
    public static String encryptStringToMD5(String input) {
        try {
            // Crie uma instância de MessageDigest com o algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Converta a string de entrada para um array de bytes e calcule o hash
            byte[] inputBytes = input.getBytes();
            byte[] hashBytes = md.digest(inputBytes);

            // Converta o hash de bytes para uma representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                hexString.append(String.format("%02x", hashByte));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Lidar com exceção, se o algoritmo MD5 não for suportado
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "MinhaSenhaSecreta123";
        String md5Hash = encryptStringToMD5(input);
        System.out.println("String original: " + input);
        System.out.println("Hash MD5: " + md5Hash);
    }
}