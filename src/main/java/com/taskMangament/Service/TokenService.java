package com.taskMangament.Service;


import com.taskMangament.Entity.Roles;
import com.taskMangament.Entity.User;
import com.taskMangament.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private final static long JWT_Token_VALIDITY = 5 * 60 * 60;  // 18000 seconds
    private final UserRepository userRepository;


//    public String createToken(User user, String secret) {
//
//        String methodName = "createToken";
//
//        logger.info("Method {} : User {} request new token on {}", methodName, user.getUsername(), LocalDateTime.now());
//
//        Claims claims = Jwts.claims().setId(UUID.randomUUID().toString()).setSubject(user.getUsername());
//
//        List<String> roles = new ArrayList<>();
//        for (Roles role : user.getRoles()) {
//            roles.add(role.getName());
//        }
//
//        claims.put("roles", roles);
//
//        Date validity = new Date(System.currentTimeMillis() + JWT_Token_VALIDITY * 1000);  // 5 hours = 18,000,000 milliseconds
//        logger.info("expiration Date :" + validity);
//
//        String tokenString = Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//        userRepository.save(user);
//        return  tokenString;
//    }
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
         long jwtExpiration =8640000L;
        return buildToken(extraClaims, userDetails,jwtExpiration);
    }
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }
    private Key getSigningKey() {
//        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretKey = "586B633834416E396D7436753879382F423F4428482B4C6250655367566B5970";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
