package uz.pdp.olx.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.olx.enitiy.User;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret-key}")
    private String secret;

    @Value("${jwt.token.expiration-mills}")
    private Long expiry;

    public String generateToken(final User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .id(String.valueOf(user.getId()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .claim("email",user.getEmail())
                .signWith(key())
                .compact();
    }

    public boolean isValid(String token) {
        Claims claims = parseAllClaims(token);
        Date expiryDate = extractExpiryDate(claims);
        return expiryDate.after(new Date());
    }

    public Claims parseAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Date extractExpiryDate(Claims claims) {
        return claims.getExpiration();
    }

    public SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
