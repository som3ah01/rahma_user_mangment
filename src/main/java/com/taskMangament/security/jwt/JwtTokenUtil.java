package com.taskMangament.security.jwt;


import com.taskMangament.Entity.User;
import com.taskMangament.Service.TokenService;
import com.taskMangament.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final JwtTokenProperties tokenProperties;

    private final CustomUserDetailsService customUserDetailsService;

    private final TokenService tokenService;

    private String secret;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(tokenProperties.getSecretKey().getBytes());
    }


    public String generateToken(UserDetails userDetails) {
        return tokenService.generateToken(userDetails);
    }

    public Authentication getAuthenticated(String token) {
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token){

        return getUserClaims(token, Claims::getSubject);
    }

    public <T> T getUserClaims(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = getAllUserClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims getAllUserClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean isValidToken(String token, UserDetails userDetails){
        final String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // to return expiration date
    public Date getExpirationDate(String token) {
        return getUserClaims(token, Claims::getExpiration);
    }

    // check if expired
   public boolean isTokenExpired(String token){
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
   }
    private Key getSigningKey() {
//        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretKey = "586B633834416E396D7436753879382F423F4428482B4C6250655367566B5970";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
