package com.company;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by Julian on 02.03.2015.
 */
public class ClientInputThread extends Thread {
    Scanner scanner;
    Chat chat;
    String username;

    public ClientInputThread(Scanner scanner, Chat chat) {
        this.scanner = scanner;
        this.chat = chat;
        this.username = "";
    }
    public ClientInputThread(Chat chat, Scanner scanner) {
        this.scanner = scanner;
        this.chat = chat;
        this.username = "";
    }
    public ClientInputThread(Scanner scanner, Chat chat, String username) {
        this.scanner = scanner;
        this.chat = chat;
        this.username = username += ": ";
    }
    public ClientInputThread(Chat chat, Scanner scanner, String username) {
        this.scanner = scanner;
        this.chat = chat;
        this.username = username += ": ";
    }
    public void run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (!input.equals("")){
                if (input.equals("exit")) System.exit(0);
                try {
                    chat.sendMessage(username + input);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}