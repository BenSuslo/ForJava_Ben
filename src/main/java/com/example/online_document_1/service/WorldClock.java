package com.example.online_document_1.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface WorldClock extends Remote {
    /**
     * Java的RMI规定此接口必须派生自java.rmi.Remote，并在每个方法声明抛出RemoteException
     * @param zoneId
     * @return
     * @throws RemoteException
     */
    LocalDateTime getLocalDateTime(String zoneId) throws RemoteException;
}

