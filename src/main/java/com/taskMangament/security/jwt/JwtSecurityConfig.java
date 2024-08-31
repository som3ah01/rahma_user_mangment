/*

package com.taskMangament.security.jwt;

import com.taskMangament.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@EnableWebSecurity
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter customAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenUtil,customUserDetailsService);

        http.exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

    }
}

*/
