//package com.db2020.delivery.config;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.http.HttpSession;
//import javax.websocket.HandshakeResponse;
//import javax.websocket.server.HandshakeRequest;
//import javax.websocket.server.ServerEndpointConfig;
//
//@Slf4j
//public class GetHttpSessionForWebSocket extends ServerEndpointConfig.Configurator{
//
//    @Override
//    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
//
//        HttpSession httpSession = (HttpSession)request.getHttpSession();
//
//        System.out.println(request.getHeaders().get("sec-websocket-protocol"));
////        System.out.println(request.getRequestURI());
////        System.out.println(httpSession);
////        System.out.println(httpSession);
////        config.getUserProperties().put("JWT", request.getHeaders().get("sec-websocket-protocol"));
////        if(httpSession != null){
////            config.getUserProperties().put("PRIVATE_HTTP_SESSION", httpSession);
////        }
//    }
//
//}
