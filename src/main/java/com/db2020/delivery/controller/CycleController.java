package com.db2020.delivery.controller;

import com.db2020.delivery.model.Response;
import com.db2020.delivery.service.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3002"}, allowCredentials = "true")
public class CycleController {

    @Autowired
    CycleService cycleSerivce;

    @GetMapping("/company/cyclelist")
    public ResponseEntity<?> cycle_list(@RequestParam HashMap<String, Object> param){
        Response result = null;
        try {
            result = new Response(true, "Success", "", "", cycleSerivce.selectCycle_list(param));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/company/cycle/{motorcycle_seq}")
    public ResponseEntity<?> cycle_Info(@PathVariable HashMap<String, Object> param){
        Response result = null;
        try {
            result = new Response(true, "Success", "", "", cycleSerivce.selectCycle_Info(param));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new Response(false, "Error", "", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
