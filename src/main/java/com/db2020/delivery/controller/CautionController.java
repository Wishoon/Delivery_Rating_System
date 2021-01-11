package com.db2020.delivery.controller;

import com.db2020.delivery.model.Response;
import com.db2020.delivery.service.CautionService;
import com.db2020.delivery.service.TokenService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/company")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002", "http://192.168.64.4:3000", "http://192.168.64.4:3002"}, allowCredentials = "true")
public class CautionController {

    @Autowired
    TokenService tokenService;
    @Autowired
    CautionService cautionService;

    // 경고 상세 정보 가져오기
    @GetMapping("/caution/{order_seq}")
    public ResponseEntity<?> caution_select(@PathVariable HashMap<String, Object> param, HttpServletRequest request){
        Response result = null;
        try {
            String token = request.getHeader("Authorization");
            param.put("member_id", tokenService.isId(token).get("member_id"));

            result = new Response(true, "Success", "", "", cautionService.selectDetailCaution(param));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    // 경고 등록
    @PostMapping("/caution")
    public ResponseEntity<?> caution_insert(@RequestBody HashMap<String, Object> param, HttpServletRequest request){
        Response result = null;
        try {
            String token = request.getHeader("Authorization");
            param.put("member_id", tokenService.isId(token).get("member_id"));

            String caution_entry_seq = String.valueOf(param.get("caution_title"));
            if(caution_entry_seq.equals("배달늦음")){
                param.put("caution_entry_seq", 1);
            } else if(caution_entry_seq.equals("오배달")){
                param.put("caution_entry_seq", 2);
            } else if(caution_entry_seq.equals("불친절")){
                param.put("caution_entry_seq", 3);
            } else if(caution_entry_seq.equals("음식 분실")){
                param.put("caution_entry_seq", 4);
            } else if(caution_entry_seq.equals("직접 입력")){
                param.put("caution_entry_seq", 5);
            }
            System.out.println(param.toString());
            cautionService.insertCaution(param);
            result = new Response(true, "Success", "", "", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}

