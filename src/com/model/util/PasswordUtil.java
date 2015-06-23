package com.model.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

import java.util.Random;

/**
 * Created by Enkhbat on 14/12/01.
 *
 */
public class PasswordUtil {
    private static final char[] passwordChars;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            tmp.append(ch);
        }
        passwordChars = tmp.toString().toCharArray();
    }

    private static final Random random = new Random();
    private static final char[] buf = new char[8];

    public static String randomPassword() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = passwordChars[random.nextInt(passwordChars.length)];
        }
        return new String(buf);
    }


    /**
     * Өгсөн текстийг шифрлэгэж бууцаана.
     *
     * @param plainPassword энгийн текст
     * @return шифрлэгдсэн текст
     */
    public static String passwordEncryptor(String plainPassword) {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String encryptedPassword = encryptor.encryptPassword(plainPassword);
        return encryptedPassword;
    }

    /**
     * Энгийн текстийг шифрлэгдсэн текстэй харицуулан
     * таарч байвал true,тарахгүй байвал false утга буцна.
     *
     * @param plainPassword     энгийн текст
     * @param encryptedPassword шифрлэгдсэн текст
     * @return true or false
     */
    public static boolean checkPassword(String plainPassword, String encryptedPassword) {
        return new StrongPasswordEncryptor().checkPassword(plainPassword, encryptedPassword);
    }

}
