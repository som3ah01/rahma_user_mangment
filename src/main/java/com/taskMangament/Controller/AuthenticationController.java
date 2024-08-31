package com.taskMangament.Controller;

import com.taskMangament.Entity.User;
import com.taskMangament.Service.TokenService;
import com.taskMangament.model.AuthenticationRequest;
import com.taskMangament.model.AuthenticationResponse;
import com.taskMangament.repository.UserRepository;
import com.taskMangament.security.CustomUserDetailsService;
import com.taskMangament.security.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {


    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/test")
    public String test(){
        var pass =passwordEncoder.encode("pass");
        return "Hello :: " + pass;
    }
    @GetMapping("/test/{pass}")
    public String test(@PathVariable String pass){
        var encoded =passwordEncoder.encode(pass);
        return "Encoded :: " + encoded;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        var user = User.builder()
//                .Id(authenticationRequest.getId())
                .username(authenticationRequest.getUsername())
                .password(authenticationRequest.getPassword())
                .email(authenticationRequest.getEmail())
                .roles(new HashSet<>())
                .build();
        final String token = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest loginRequest) {
//        if(true){
//            return ResponseEntity.ok(loginRequest);
//        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenUtil.generateToken(customUserDetailsService.loadUserByUsername(loginRequest.getUsername()));
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}