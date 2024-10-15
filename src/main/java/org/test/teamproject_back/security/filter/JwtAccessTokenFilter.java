package org.test.teamproject_back.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.test.teamproject_back.entity.User;
import org.test.teamproject_back.repository.UserMapper;
import org.test.teamproject_back.security.jwt.JwtProvider;
import org.test.teamproject_back.security.principal.PrincipalUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAccessTokenFilter extends GenericFilter {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.equals("/admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String bearerAccessToken = httpServletRequest.getHeader("Authorization");
        System.out.println("bearerAccessToken: " + bearerAccessToken);
        if (bearerAccessToken == null || bearerAccessToken.isBlank()) {
            System.out.println(bearerAccessToken);
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("after");
            return;
        }

        String accessToken = jwtProvider.removeBearer(bearerAccessToken);
        Claims claims = null;

        try {
            claims = jwtProvider.getClaims(accessToken);
            Long userId = ((Integer) claims.get("userId")).longValue();
            User user = userMapper.findById(userId);
            if (user == null) {
                throw new JwtException("해당 ID(" + userId + ")의 사용자 정보를 찾지 못했습니다.");
            }
            PrincipalUser principalUser = user.toPrincipal();
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            e.printStackTrace();
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}