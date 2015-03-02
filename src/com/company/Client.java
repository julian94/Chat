package com.company;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost/Chat";
        Chat chat = (Chat) Naming.lookup(url);
        int latestMessage = 0;

        String input = "";
        Scanner leserFraKommandovindu = new Scanner(System.in);
        System.out.println("Welcome to my chat program./nPlease insert a username.");
        String username = leserFraKommandovindu.nextLine();
        ClientInputThread cit = new ClientInputThread(chat, leserFraKommandovindu);
        cit.start();
        while (true) {
            Thread.sleep(500);
            if (chat.checkNew(latestMessage)) {
                ArrayList<String> messages = chat.getNew(latestMessage);
                printMessages(messages);
                latestMessage += messages.size();
            }
        }
    }

    private static void printMessages(ArrayList<String> messages) {
        if (messages != null) {
            for (String message : messages) {
                System.out.println(message);
            }
        }
    }
}