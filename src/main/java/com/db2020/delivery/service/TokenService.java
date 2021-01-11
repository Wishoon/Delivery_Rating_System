package com.db2020.delivery.service;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenService {
    private String secretKey = "1234";

    public String makeCompanyJwt(HashMap<String, Object> delivererVO) {
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put("member_id", delivererVO.get("member_id"));
        claimsMap.put("member_role", delivererVO.get("member_role"));

        // 토큰 생성
        String tokenString = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .setExpiration(expireTime)
                .setClaims(claimsMap)
                .compact();

        return tokenString;
    }

    public String makeDelivererJwt(HashMap<String, Object> delivererVO) {
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put("member_id", delivererVO.get("member_id"));
        claimsMap.put("member_nm", delivererVO.get("member_nm"));
        claimsMap.put("member_grade", delivererVO.get("grade_nm"));
        claimsMap.put("member_score", delivererVO.get("deliverer_score"));
        claimsMap.put("member_caution", delivererVO.get("member_caution"));
        claimsMap.put("member_role", delivererVO.get("member_role"));


        // 토큰 생성
        String tokenString = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .setExpiration(expireTime)
                .setClaims(claimsMap)
                .compact();

        return tokenString;
    }

    public boolean isUsableToken(String tokenString) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(tokenString).getBody(); // 정상 수행 시 정상 토큰
            log.info("Authorization : {member_id : " + claims.get("member_id") + ", member_role: " + claims.get("member_role") + "}");
            return true;
        } catch (ExpiredJwtException exception) {
            log.info("토큰 만료");
            return false;
        } catch (JwtException exception) {
            log.info("토큰 변조");
            return false;
        }
    }

    public HashMap<String, Object> isRoleJudge(String tokenString) {

        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(tokenString).getBody(); // 정상 수행 시 정상 토큰
//        log.info("Authorization : {member_id : " + claims.get("member_id") + ", member_role: " + claims.get("member_role") + "}");

        HashMap<String, Object> param = new HashMap<>();

        param.put("member_role", claims.get("member_role"));
        param.put("member_score", claims.get("member_score"));

        return param;
    }

    public HashMap<String, String> isMember(String tokenString) {

        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(tokenString).getBody(); // 정상 수행 시 정상 토큰
        log.info("Authorization : {member_id : " + claims.get("member_id") + ", member_role: " + claims.get("member_role") + "}");

        HashMap<String, String> param = new HashMap<>();

        param.put("member_id", (String)claims.get("member_id"));
        param.put("member_nm", (String)claims.get("member_nm"));
        param.put("member_grade", (String)claims.get("member_grade"));
        param.put("member_score", (String)claims.get("member_score"));
        param.put("member_caution", (String)claims.get("member_caution"));

        return param;
    }

    public HashMap<String, String> isId(String tokenString){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(tokenString).getBody(); // 정상 수행 시 정상 토큰

        HashMap<String, String> param = new HashMap<>();

        System.out.println((String)claims.get("member_id"));
        param.put("member_id", (String)claims.get("member_id"));
        return param;
    }
}
