package com.example.technicalassessment.util;

import com.example.technicalassessment.domain.Permission;
import com.example.technicalassessment.domain.Role;
import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtDecoder jwtDecoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        Jwt jwt = jwtDecoder.decode(token);
        String email = jwt.getSubject();

        // Lấy user từ DB
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            filterChain.doFilter(request, response);
            return;
        }


        Role role = user.getRole();
        List<Permission> permissionList = (role != null) ? role.getPermissions() : Collections.emptyList();

        List<GrantedAuthority> authorities = permissionList.stream()
                .map(Permission::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());


        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(email, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
