package com.company;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    static String username = "noob";
    public static boolean encryption = false;
    private static int latestMessage = 0;
    private static String[] cipher;

    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/Chat";

        String input = "";
        Scanner leserFraKommandovindu = new Scanner(System.in);
        System.out.println("Welcome to my chat program.");
        System.out.println("Please insert a username.");
        username = leserFraKommandovindu.nextLine();
        System.out.println("What is the hostname?");
        url = "rmi://" + leserFraKommandovindu.nextLine() + "/Chat";
        System.out.println(url);
        Chat chat = (Chat) Naming.lookup(url);

        System.out.println("Enter OTP filename (blank for no encryption)");
        String otpFile = leserFraKommandovindu.nextLine();
        otpFile = "TestCipher"; // REMOVE AFTER TESTING
        if (!otpFile.equals("")) {
            cipher = OTPGen.readCleanFile(otpFile);
            encryption = true;
        }

        ClientInputThread cit;
        if (encryption) cit = new ClientInputThread(chat, leserFraKommandovindu, username, cipher);
        else cit = new ClientInputThread(chat, leserFraKommandovindu, username);

        cit.start();
        while (true) {
            Thread.sleep(500);
            if (chat.checkNew(latestMessage)) {
                ArrayList<String> messages = chat.getNew(latestMessage);
                printMessages(messages);
            }
        }
    }

    private static void printMessages(ArrayList<String> messages) {
        if (messages != null) {
            for (String message : messages) {
                if (!message.startsWith(username)) {
                    String[] split = message.split(": ");
                    //System.out.println(message);
                    System.out.println(split[0] + ": " + OTPGen.decrypt(split[1],cipher[latestMessage]));
                }
                latestMessage++;
            }
        }
    }
}