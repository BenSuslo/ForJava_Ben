package com.example.online_document_1.service;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class WorldClockServer {
    /**
     * 判断rmi服务端是否开启
     * @return
     */
    public static boolean hasOpen(){
        Socket Skt;
        String host = "localhost";
        try{
            Skt = new Socket(host, 1099);
            return true;
        }catch (IOException e){

        }
        return false;
    }

    /**
     * 开启rmi服务
     * @throws RemoteException
     */
    public static void provideLocalTime() throws RemoteException{
        System.out.println("create World clock remote service...");
        // 实例化一个WorldClock:
        WorldClock worldClock = new WorldClockService();
        // 将此服务转换为远程服务接口:
        WorldClock skeleton = (WorldClock) UnicastRemoteObject.exportObject(worldClock, 0);
        // 将RMI服务注册到1099端口:
        Registry registry = LocateRegistry.createRegistry(1099);
        // 注册此服务，服务名为"WorldClock":
        registry.rebind("WorldClock", skeleton);
    }
}
