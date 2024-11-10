package org.interaction.interactionbackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.interaction.interactionbackend.enums.Role;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Autowired
    private UserRepository userRepository;

    private final String SECRET_KEY = "my_secret_key"; // (demo here)

    public String generateToken(User user) {
        return Jwts.builder()
                .addClaims(Map.of("role", user.getRole()))
                .addClaims(Map.of("id", user.getId()))
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10小时过期
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public Integer extractId(String token) {
        return (Integer) extractClaims(token).get("id");
    }

    public Role extractRole(String token) {
        return Role.valueOf((String) extractClaims(token).get("role"));
    }

    public boolean isTokenValid(String token) {
        String email = extractEmail(token);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}


