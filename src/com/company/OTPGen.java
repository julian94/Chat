package com.company;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Julian on 23.02.2015.
 */
public class OTPGen {
    static char[] c = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X',
            'Y','Z','0','1','2','3','4','5','6','7','8','9',' ','a','b','c','d','e','f','g','h','i','j','k','l','m','m',
            'n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public static char[] genOTP(int length){
        char[] result = new char[length];
        Random r = new SecureRandom();
        for (int i = 0; i < length; i++){
            result[i] = c[r.nextInt(c.length)];
        }
        return result;
    }

    public static String charToString(char[] c) {
        String s = "";
        for (char a: c) {
            s += a;
        }
        return s;
    }

    public static String getPages(int pages, int lines, int linelength) {
        String out = "";
        for (int p = 0; p < pages; p++) {
            out += ("\n" + "Page " + p + "\n");
            for (int line = 0; line < lines; line++) {
                out += (charToString(genOTP(linelength)) + "\n");
            }
        }
        return out;
    }

    public static String getCleanLines(int lines, int linelength) {
        String out = "";
        for (int line = 0; line < lines; line++) {
            out += (charToString(genOTP(linelength)) + "\n");
        }
        return out;
    }

    public static boolean makeFile(String filename, int pages, int lines, int linelength) {
        try {
            PrintWriter pw = new PrintWriter(filename);
            pw.write(getPages(pages, lines, linelength));
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean makeCleanFile(String filename, int lines, int linelength) {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            //Writer pw = new Writer(filename);
            bw.write(getCleanLines(lines, linelength));
            bw.close();
            fw.close();
        } catch (Exception e) {
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
    }

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
    }
}