package com.company;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by Julian on 03.03.2015.
 */
public class OneTimeBytes {
    public static byte[] getBytes(int nr) {
        SecureRandom sr = new SecureRandom();
        byte[] bytes = new byte[nr];
        sr.nextBytes(bytes);
        return bytes;
    }

    public static char[] getChars(int nr) {
        char[] c = new char[nr];
        byte[] b = getBytes(nr);
        for (int i = 0; i < nr; i++) {
            c[i] = (char) b[i];
        }
        return c;
    }
    public static boolean writeFile(String filename, int lines, int linelength) {
        try {
            PrintWriter pw = new PrintWriter(filename);
            for (int i = 0; i < lines; i++) {
                pw.write(getChars(linelength));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String[] readCleanFile(String filename) {
        ArrayList<String> messages = new ArrayList();
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String message = "";
            while (br.ready()) {
                message = br.readLine();
                messages.add(message);

            }
            br.close();
            fr.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String[] output = new String[messages.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = messages.get(i);
        }
        return output;
    }/*

    public static String encrypt(String text, String cipher) {
        text = text.toUpperCase();
        cipher = cipher.toUpperCase();
        char[] letters = text.toCharArray();
        char[] cipherLetters = cipher.toCharArray();
        char[] ciphertext = new char[letters.length];
        int k,l;
        for (int i = 0; i < letters.length; i++) {
            for (k = 0; k < c.length; k++) {
                if (letters[i] == c[k]) break;
            }
            for (l = 0; l < c.length; l++) {
                if (cipherLetters[i] == c[l]) break;
            }
            ciphertext[i] = (k+l) < c.length ? c[k+l] : c[(k+l)-c.length];
        }
        return new String(ciphertext);
    }

    public static String decrypt(String ciphertext, String cipher) {
        ciphertext = ciphertext.toUpperCase();
        cipher = cipher.toUpperCase();
        char[] letters = ciphertext.toCharArray();
        char[] cipherLetters = cipher.toCharArray();
        char[] text = new char[letters.length];
        int k,l;
        for (int i = 0; i < letters.length; i++) {
            for (k = 0; k < c.length; k++) {
                if (letters[i] == c[k]) break;
            }
            for (l = 0; l < c.length; l++) {
                if (cipherLetters[i] == c[l]) break;
            }
            text[i] = l <= k ? c[k-l] : c[c.length - (l-k)];
        }
        return new String(text);
    }*/
}
