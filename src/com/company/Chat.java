package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Julian on 02.03.2015.
 */
public interface Chat extends Remote {
    boolean sendMessage(String message) throws RemoteException;
    boolean checkNew(int latestMessage) throws RemoteException;
    ArrayList<String> getNew(int latestMessage) throws RemoteException;
    int messageCount() throws RemoteException;
}
