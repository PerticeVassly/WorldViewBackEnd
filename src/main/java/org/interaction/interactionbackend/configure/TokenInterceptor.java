package org.interaction.interactionbackend.configure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.interaction.interactionbackend.enums.Role;
import org.interaction.interactionbackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || !jwtUtil.isTokenValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return false;
        }
        String email = jwtUtil.extractEmail(token);
        Role role = jwtUtil.extractRole(token);
        Integer userId = jwtUtil.extractId(token);
        request.setAttribute("email", email);
        request.setAttribute("role", role);
        request.setAttribute("userId", userId);
        return true;
    }
}
