package com.example.demo.security;

import com.example.demo.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

     @Autowired
        private UserDetailsServiceImpl userDetailsService;

 
    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

    String path = request.getRequestURI();


    if (path.startsWith("/auth/")) {
        filterChain.doFilter(request, response);
        return;
    }

    final String authHeader = request.getHeader("Authorization");
    String username = null;
    String token = null;

    if(authHeader != null && authHeader.startsWith("Bearer ")){
        token = authHeader.substring(7);
        try{
            username = jwtUtil.extractUsername(token);
        }catch (Exception e){
           
        }
    }

    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        var userDetails = userDetailsService.loadUserByUsername(username);
        if(jwtUtil.validateToken(token, userDetails.getUsername())){
            var authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    filterChain.doFilter(request, response);
}
    }
