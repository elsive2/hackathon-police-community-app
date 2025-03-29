package com.hackathon.police_community_app.backend.filter;

import com.hackathon.police_community_app.backend.authentication.SmsCodeAuthenticationToken;
import com.hackathon.police_community_app.backend.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = authHeader.substring(BEARER_PREFIX.length());

        try {
            String phone = jwtUtil.getSubjectFromToken(token);

            if (StringUtils.isNotEmpty(phone) && SecurityContextHolder.getContext().getAuthentication() == null) {

                if (jwtUtil.isValidToken(token)) {
                    var authenticationToken = new SmsCodeAuthenticationToken(
                            jwtUtil.getIdFromToken(token),
                            phone,
                            Collections.singletonList(new SimpleGrantedAuthority(jwtUtil.getRoleFromToken(token)))
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }

        filterChain.doFilter(request, response);
    }
}
