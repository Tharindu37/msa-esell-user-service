package com.esell.esell_user_service.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "5f2a87a69348c0781f9141b8cd167a062a2f595b9991a56166ef47cc16e3d5b7debe78fd4394d75076f8153077469ca74b946510ea3ac487e53baa33d1e790918a7d4484dfbd8f17395a59123901ecd79e33bbc97343cd8313355fa4645a083352548e4f014f2951045b60207da679e6979c0b9063e828a50c9fd8ff4be5b518c7ff5b4d4cd68d0aae751ac634e78ddfa08a36fff59d1403449d5bc45172cbe0eb186e587e96a2a2f6dcb7d0e712276e954a33c6e14c03d88ca8276e5aaf189cfaa299604c8da6ad205d687c8d37a0c65a08a7ef26fd4349f1a0387f1efc1a541f640700f0bc5d829f4d06d4c0521509dc68bb53cb8e502589f35b85bb5a448d";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        return  getClaims(token).getSubject();
    }

    public boolean validateToken(String token, String email) {
        return (email.equals(extractEmail(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
    }
}
