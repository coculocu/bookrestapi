package com.example.bookmngmt.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.bookmngmt.service.JwtTokenService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = request.getHeader("Authorization");

            if (token != null && token.startsWith("Bearer ") ) {
                // Validate the JWT token
                String tokenWithoutBearer = token.substring(7);
                Authentication authentication = jwtTokenService.getAuthentication(tokenWithoutBearer);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                this,
                UsernamePasswordAuthenticationFilter.class
        );

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/swagger-ui/**", "/api-docs", "/api-docs/**").permitAll()
                .anyRequest().authenticated());

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
    
}