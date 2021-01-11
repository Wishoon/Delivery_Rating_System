package com.db2020.delivery.controller;

import com.db2020.delivery.entity.LogoutVO;
import com.db2020.delivery.service.AuthService;
import com.db2020.delivery.service.TokenService;
import com.db2020.delivery.model.Response;
import com.db2020.delivery.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002", "http://192.168.64.4:3000", "http://192.168.64.4:3002"}, allowCredentials = "true")
@RequestMapping("/v1/company")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    AuthService authService;

    @Autowired
    TokenService tokenService;

    // 주문 목록 가져오기
    @GetMapping("/order/list")
    public ResponseEntity<?> orderList(@RequestParam HashMap<String, Object> param, HttpServletRequest request){
        Response result = null;
        try {
            String token = request.getHeader("Authorization");
            param.put("member_id", tokenService.isId(token).get("member_id"));
            result = new Response(true, "Success", "", "", orderService.selectOrderList(param));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    // 경고 리스트 가져오기
    @GetMapping("/order/endlist")
    public ResponseEntity<?> cautionList(HttpServletRequest request){
        Response result = null;
        try {
            HashMap<String, Object> param = new HashMap<>();
            String token = request.getHeader("Authorization");
            param.put("member_id", tokenService.isId(token).get("member_id"));

            result = new Response(true, "Success", "", "", orderService.selectCautionList(param));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order/match")
    public ResponseEntity<?> orderMatch(@RequestBody HashMap<String, Object> param, HttpServletRequest request){
        Response result = null;
        try {
//            String token = request.getHeader("Authorization");
//            param.put("member_id", tokenService.isId(token).get("member_id"));
            System.out.println(param.toString());
            orderService.insertMatch(param);
            result = new Response(true, "Success", "", "", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order/startmatch")
    public ResponseEntity<?> orderStartMatch(@RequestBody HashMap<String, Object> param, HttpServletRequest request){
        Response result = null;
        try {
            String token = request.getHeader("Authorization");
            param.put("member_id", tokenService.isId(token).get("member_id"));
            System.out.println(param.toString());
            System.out.println("sss");
            orderService.startMatch(param);
            result = new Response(true, "Success", "", "", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order/endmatch")
    public ResponseEntity<?> orderEndMatch(@RequestBody HashMap<String, Object> param, HttpServletRequest request){
        Response result = null;
        try {
//            String token = request.getHeader("Authorization");
//            param.put("member_id", tokenService.isId(token).get("member_id"));
            System.out.println(param.toString());
            orderService.endMatch(param);
            result = new Response(true, "Success", "", "", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }


}
