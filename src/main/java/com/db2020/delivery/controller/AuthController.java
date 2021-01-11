package com.db2020.delivery.controller;
import javax.websocket.*;
import javax.websocket.RemoteEndpoint.Basic;
import com.db2020.delivery.config.CustomSpringConfigurator;
import com.db2020.delivery.entity.DelivererVO;
import com.db2020.delivery.entity.LogoutVO;
import com.db2020.delivery.entity.MainVO;
import com.db2020.delivery.service.TokenService;
import com.db2020.delivery.model.Response;
import com.db2020.delivery.service.AuthService;
import com.db2020.delivery.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.log.LogOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002", "http://192.168.64.4:3000", "http://192.168.64.4:3002"}, allowCredentials = "true")
@ServerEndpoint(value = "/echo", configurator = CustomSpringConfigurator.class) /*, encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class} */
@Controller
@RequestMapping("/v1")
public class AuthController {

    private static final java.util.Map<Session, String> sessions = java.util.Collections.synchronizedMap(new java.util.HashMap<Session, String>());
    private static final java.util.Map<Session, String> sessions_store = java.util.Collections.synchronizedMap(new java.util.HashMap<Session, String>());
    private static final java.util.Map<Session, Integer> sessions_score = java.util.Collections.synchronizedMap(new java.util.HashMap<Session, Integer>());

    @Autowired
    TokenService tokenService;
    @Autowired
    AuthService authService;
    @Autowired
    OrderService orderService;

//    @GetMapping("/index1")
//    public String signIn(@RequestParam HashMap<String, Object> param, HttpServletRequest request) {
//        System.out.println("sss");
//        HashMap<String, Object> delivererVO = authService.signIn(param);
//        HttpSession session = request.getSession();
//        session.setAttribute("member_id", delivererVO.get("member_id"));
//        return "index";
//    }

    @PostMapping("/auth/deliverer")
    public ResponseEntity<?> userSignUp(@RequestBody HashMap<String, Object> param, HttpServletRequest request) {
        Response result = null;
        try {
            HashMap<String, Object> delivererVO = authService.deliverer_signIn(param);
            log.info("배달원 로그인 결과 값: " + delivererVO.toString());
            MainVO mainVO = authService.mainPage(param);
            System.out.println(mainVO.toString());
            if (delivererVO.get("deliverer_score") == null) {
                delivererVO.put("Authorization", tokenService.makeCompanyJwt(delivererVO));
            } else {
                delivererVO.put("Authorization", tokenService.makeDelivererJwt(delivererVO));
            }

            delivererVO.put("deliverer_todayCnt", mainVO.getDeliverer_todayCnt());
            delivererVO.put("deliverer_monthCnt", mainVO.getDeliverer_monthCnt());
            delivererVO.put("deliverer_totalCnt", mainVO.getDeliverer_totalCnt());
            delivererVO.put("caution_todayCnt", mainVO.getCaution_todayCnt());
            delivererVO.put("caution_monthCnt", mainVO.getCaution_monthCnt());
            delivererVO.put("caution_totalCnt", mainVO.getCaution_totalCnt());
            delivererVO.put("deliverer_todayPrice", mainVO.getDeliverer_todayPrice());
            delivererVO.put("deliverer_monthPrice", mainVO.getDeliverer_monthPrice());
            delivererVO.put("deliverer_totalPrice", mainVO.getDeliverer_totalPrice());

            result = new Response(true, "Success", "", "", delivererVO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/shop")
    public ResponseEntity<?> shopSignUp(@RequestBody HashMap<String, Object> param, HttpServletRequest request) {
        Response result = null;
        try {
            HashMap<String, Object> signVO = authService.shop_signIn(param);
            log.info("점주 로그인 결과 값: " + signVO.toString());
            if (signVO.get("deliverer_score") == null) {
                signVO.put("Authorization", tokenService.makeCompanyJwt(signVO));
            } else {
                signVO.put("Authorization", tokenService.makeDelivererJwt(signVO));
            }
            result = new Response(true, "Success", "", "", signVO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deliverer/logout")
    public ResponseEntity<?> deliverer_logout(@RequestBody HashMap<String, Object> param) {
        Response result = null;
        try {
            String token = String.valueOf(param.get("Authorization"));
//            String token = request.getHeader("Authorization");
            param.put("member_id", tokenService.isId(token).get("member_id"));
            HashMap<String, Object> logoutVO = authService.deliverer_logout(param);
            log.info("로그아웃 반환 값: " + logoutVO.toString());
            result = new Response(true, "Success", "", "", logoutVO);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        log.info("Open session id:" + session.getId());
//        log.info("세션 생성 갯수: " + sessions.size() + "개");
        try {
            final Basic basic = session.getBasicRemote();
//            basic.sendText("대화방에 연결 중 입니다....");
        } catch (Exception e) {

        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException{
        try {
            log.info("세션 갯수:" + sessions.size());
            boolean check = false;
            String[] data = message.split(",");
            log.info("메시지 값: " + session.getId() + ": "+ message);
            log.info("Token 값: " + data[0]);
            HashMap<String, Object> param = tokenService.isRoleJudge(data[0]);
            final Basic basic = session.getBasicRemote();
            for (Map.Entry<Session, String> entry : sessions.entrySet()) {
                // 세션 리스트에 새로 들어온 토큰 값이 있는 경우
//                if(entry.getValue().equals(data[0])){
                if(entry.getValue().equals(data[0])){
                    check = true;
                    // 점주 일 경우
                    if(param.get("member_role").equals("ROLE_SHOP")){
                        if(data.length == 3) {
                            sendAccept(session, message);
                        }
                        else{
                            sendAll(session, message, data[0]);
                        }
                    }
                    // 배달원 일 경우
                    else{
                        HashMap<String, String> member = tokenService.isMember(data[0]);
                        sendChoice(session, message, member);
                    }
                    break;
                }
            }
            if(check == false){
                log.info("세션 이름: " + session);
                sessions.put(session, data[0]);
                // 점주 일 경우
                if(param.get("member_role").equals("ROLE_SHOP")){
                    log.info("점주가 로그인 하였습니다.");
//                    basic.sendText("점주 메시지가 로그온 되었습니다.: " +  param.get("member_nm"));
                    sessions_store.put(session, data[0]);
                }
                // 배달원 일 경우

                else{
                    log.info("배달원이 로그인 하였습니다.");
//                    basic.sendText("배달원 메시지가 로그온 되었습니다.: " +  param.get("member_nm"));
                    sessions_score.put(session, Integer.valueOf((String)param.get("member_score")));
                }
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // 점주가 배달원들에게 메시지 보내기
    public void sendAll(Session ss, String message, String token) throws IOException, InterruptedException {
        int count = 0;
        String[] data = message.split(",");
        // 순서 정렬 부분
        List<Map.Entry<Session, Integer>> entries;
        entries = sessions_score.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());
//        메시지에서 날아온 오더 시퀀스를 통해 오더 정보 출력 (받아야 하는 값: token, 주문 번호, 내용)
        HashMap<String, Object> param = orderService.orderInfo(data[1]);
        // 정렬된 배달원들에게 메시지 보내기
        for (Map.Entry<Session, Integer> entry : entries) {
            log.info("배달원 점수: " + entry.getValue() + "점");
            Session ws_ss = entry.getKey();

            if(count == 2){
                Thread.sleep(5000);
            }
            ws_ss.getBasicRemote().sendText((String)param.get("shop_nm"));
            ws_ss.getBasicRemote().sendText((String)param.get("shop_address"));
            ws_ss.getBasicRemote().sendText((String)param.get("shop_detail_address"));
            ws_ss.getBasicRemote().sendText((String)param.get("order_summary"));
            ws_ss.getBasicRemote().sendText(String.valueOf(param.get("order_seq")));
            count++;
        }
    }
    // 배달원이 점주에게 수락 메시지 보내기
    public void sendChoice(Session ss, String message, HashMap<String, String> param) throws IOException{
//        HashMap<String, Object> param = tokenSerivce.isMember(token);

        String[] data = message.split(",");
        for (Map.Entry<Session, String> entry : sessions_store.entrySet()) {
            String orderMatchYn = orderService.orderMatchYn(data[1]);
            System.out.println(orderMatchYn);
            Session ws_ss = entry.getKey();
//            String orderMatchYn = orderService.orderMatchYn(data[1]);
//            DelivererVO data = new
            // 여기는 1로 나중에 바껴야 함
//            if(data[0].equals(entry.getValue())){
                // 점주에게 보내는 메시지
            if(orderMatchYn.equals("N")) {
                ws_ss.getBasicRemote().sendText(param.get("member_id"));
                ws_ss.getBasicRemote().sendText(param.get("member_nm"));
                ws_ss.getBasicRemote().sendText(param.get("member_grade"));
                ws_ss.getBasicRemote().sendText(param.get("member_score"));
                ws_ss.getBasicRemote().sendText(param.get("member_caution"));
            }
            else{
                System.out.println("안보냄");
            }
        }
    }
    public void sendAccept(Session ss, String message) throws IOException{

        String[] data = message.split(",");
//        HashMap<String, Object> param = orderService.orderInfo(data[1]);

        for(Map.Entry<Session, String> entry : sessions.entrySet()) {
            String ws_ss = entry.getValue();
            HashMap<String, String> id = tokenService.isId(ws_ss);

            if(id.get("member_id").equals(data[2])){
                Session session = entry.getKey();
                session.getBasicRemote().sendText("true");
            }
        }
    }

    @OnClose
    public void onClose(Session session){
        log.info("종료 :" + session.getId());

        sessions.remove(session);

        for (Map.Entry<Session, String> entry : sessions_store.entrySet()) {
            if(entry.getKey().equals(session)){
                sessions_store.remove(session);
            }
        }
        for (Map.Entry<Session, Integer> entry : sessions_score.entrySet()) {
            if(entry.getKey().equals(session)){
                sessions_score.remove(session);
            }
        }
    }
}
