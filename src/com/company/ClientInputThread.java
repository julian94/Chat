package com.company;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by Julian on 02.03.2015.
 */
public class ClientInputThread extends Thread {
    Scanner scanner;
    Chat chat;
    public ClientInputThread(Scanner scanner, Chat chat) {
        this.scanner = scanner;
        this.chat = chat;
    }
    public ClientInputThread(Chat chat, Scanner scanner) {
        this.scanner = scanner;
        this.chat = chat;
    }
    public void run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (!input.equals("")){
                if (input.equals("exit")) System.exit(0);
                try {
                    chat.sendMessage(input);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}