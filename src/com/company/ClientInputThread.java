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
    String[] cipher;

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
    public ClientInputThread(Chat chat, Scanner scanner, String username, String[] cipher) {
        this.scanner = scanner;
        this.chat = chat;
        this.username = username += ": ";
        this.cipher = cipher;
    }
    public void run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (!input.equals("")){
                if (input.equals("exit")) System.exit(0);
                try {
                    int nr = chat.messageCount();
                    if (cipher == null){
                        chat.sendMessage(username + input);
                    } else {
                        chat.sendMessage(username + OTPGen.encrypt(input,cipher[nr]));
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}