package com.example.online_document_1.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.online_document_1.entity.WebSocketMsg;

/**
 * WebSocket 聊天服务端
 *
 * @see ServerEndpoint WebSocket服务端 需指定端点的访问路径
 * @see Session   WebSocket会话对象 通过它给客户端发送消息
 */

@Component
@ServerEndpoint("/online_document")
public class WebSocketService {

    /**
     * 全部在线会话
     * 使用线程安全的Map存储会话对象
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * 当客户端打开连接：
     * 1.添加会话对象
     * 2.更新在线人数
     * 3.打开rmi服务端
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineSessions.put(session.getId(), session);
        sendWebSocketMsgToAll(WebSocketMsg.jsonStr(WebSocketMsg.ENTER, "", "", onlineSessions.size()));
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     * <p>
     * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) throws NotBoundException, RemoteException {
        WebSocketMsg webSocketMsg = JSON.parseObject(jsonStr, WebSocketMsg.class);
        if(jsonStr.indexOf("getTime") != -1){
            String time = getLocalTimeService();
            sendWebSocketMsgToAll(WebSocketMsg.jsonStr(WebSocketMsg.SPEAK, webSocketMsg.getUsername(), webSocketMsg.getMsg()+"\n"+time, onlineSessions.size()));
        }else if(jsonStr.indexOf("saveToFile") != -1){
            System.out.println("file");
            saveToFile(webSocketMsg.getMsg());
        }else{
            sendWebSocketMsgToAll(WebSocketMsg.jsonStr(WebSocketMsg.SPEAK, webSocketMsg.getUsername(), webSocketMsg.getMsg(), onlineSessions.size()));
        }
    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session) {
        onlineSessions.remove(session.getId());
        sendWebSocketMsgToAll(WebSocketMsg.jsonStr(WebSocketMsg.QUIT, "", "", onlineSessions.size()));
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 公共方法：
     * 发送消息给所有人
     */
    private static void sendWebSocketMsgToAll(String msg){
        onlineSessions.forEach((id, session) -> {
            try {
                System.out.println(msg);
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 利用rmi获取当前时间
     */
    private static String getLocalTimeService() throws RemoteException, NotBoundException {
        // 打开rmi服务端
        WorldClockServer worldClockServer = new WorldClockServer();
        if(!worldClockServer.hasOpen()){
            worldClockServer.provideLocalTime();
        }
        // 打开rmi客户端
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        WorldClockClient worldClockClient = new WorldClockClient();
        String localTime = df.format(worldClockClient.getLocalTime());
        System.out.println(localTime);
        return localTime;
    }

    /**
     * 保存内容为文件
     */
    private static void saveToFile(String text){
        try (PrintStream out = new PrintStream(new FileOutputStream("F:\\MiddleWare_workspace\\mw_test.txt"))) {
            out.print(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
