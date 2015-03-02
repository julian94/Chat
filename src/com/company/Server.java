package com.company;

/**
 * Created by Julian on 02.03.2015.
 */
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception{
        Chat chat = new ChatImpl();
        String navn = "Chat";
        LocateRegistry.createRegistry(1099);
        Naming.rebind(navn,chat);
        String input = "";
        Scanner leserFraKommandovindu = new Scanner(System.in);
        System.out.println("Server is running, press return to close...");
        System.out.println(leserFraKommandovindu.nextLine());
        Naming.unbind(navn);
        System.exit(0);
    }
}