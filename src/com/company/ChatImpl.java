package com.company;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Julian on 02.03.2015.
 */
public class ChatImpl extends UnicastRemoteObject implements Chat, Serializable {
    ArrayList<String> messages = new ArrayList<String>();

    protected ChatImpl() throws RemoteException {
    }

    public synchronized void sendMessage(String message) {
        messages.add(message);
    }

    public synchronized boolean checkNew(int latestMessage){
        return latestMessage < (messages.size());
    }

    public synchronized ArrayList<String> getNew(int latestMessage) {
        if (!checkNew(latestMessage)) return null; // Returns null if no new messages.
        ArrayList<String> newMessages = new ArrayList<String>();
        for (int i = latestMessage; i < messages.size(); i++){
            newMessages.add(messages.get(i));
        }
        return newMessages;
    }
}