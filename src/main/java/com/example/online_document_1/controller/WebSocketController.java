package com.example.online_document_1.controller;

import com.example.online_document_1.service.WorldClockServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

@RestController
public class WebSocketController {

    /**
     * 登录界面
     */
    @GetMapping("/")
    public ModelAndView login(){
        System.out.println(new ModelAndView("/login"));
        return new ModelAndView("/login");
    }

    /**
     * 聊天界面
     */
    @GetMapping("/index")
    public ModelAndView index(String username, String password, HttpServletRequest request) throws UnknownHostException, RemoteException {
        if(StringUtils.isEmpty(username)){
            username = "匿名用户";
        }
        ModelAndView mav = new ModelAndView("/online_document");
        mav.addObject("username", username);
        mav.addObject("webSocketUrl", "ws://"+ InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/online_document");
        System.out.println(mav);
        return mav;
    }
}
