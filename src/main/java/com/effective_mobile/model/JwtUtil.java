package com.effective_mobile.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Утилита для работы с токенами JWT (JSON Web Tokens). Этот класс предоставляет методы для генерации, проверки и извлечения информации из токенов.
 *
 * @author [Ваше имя]
 */
@Component
public class JwtUtil {

    /**
     * Секретный ключ, используемый для подписи и проверки токенов JWT.
     */
    private final String secretKey = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    /**
     * Генерирует токен JWT для заданного пользователя.
     *
     * @param userDetails объект, содержащий информацию о пользователе
     * @return сгенерированный токен JWT
     */
    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }

    /**
     * Проверяет валидность токена JWT для заданного пользователя.
     *
     * @param token токен JWT для проверки
     * @param userDetails объект, содержащий информацию о пользователе
     * @return true, если токен действителен, false иначе
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Извлекает имя пользователя из токена JWT.
     *
     * @param token токен JWT
     * @return имя пользователя
     */
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Проверяет, истек ли срок действия токена JWT.
     *
     * @param token токен JWT
     * @return true, если токен истек, false иначе
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Извлекает дату истечения срока действия токена JWT.
     *
     * @param token токен JWT
     * @return дата истечения срока действия
     */
    private Date extractExpiration(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
    }
}
