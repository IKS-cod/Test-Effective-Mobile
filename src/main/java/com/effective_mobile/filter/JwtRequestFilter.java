package com.effective_mobile.filter;

import com.effective_mobile.model.JwtUtil;
import com.effective_mobile.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр для обработки запросов с токенами JWT. Этот фильтр проверяет наличие и валидность токена в каждом запросе.
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    /**
     * Логгер для записи информации о выполнении методов фильтра.
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    /**
     * Утилита для работы с токенами JWT, предоставляющая методы для извлечения и проверки токенов.
     */
    private final JwtUtil jwtUtil;

    /**
     * Сервис для работы с пользователями, предоставляющий информацию о пользователях.
     */
    private final UserService userService;

    /**
     * Конструктор для инициализации утилиты JWT и сервиса пользователей.
     *
     * @param jwtUtil утилита для работы с токенами JWT
     * @param userService сервис для работы с пользователями
     */
    public JwtRequestFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * Метод, выполняющийся для каждого запроса. Он проверяет наличие и валидность токена JWT в заголовке запроса.
     *
     * @param request запрос, содержащий информацию о клиенте
     * @param response ответ, который будет отправлен клиенту
     * @param chain цепочка фильтров, через которую проходит запрос
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException если возникает ошибка при чтении или записи данных
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
            logger.debug("JWT найден: {}", jwt);
        } else {
            logger.warn("JWT отсутствует или имеет неверный формат.");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                logger.info("JWT действителен для пользователя: {}", username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                logger.warn("JWT недействителен для пользователя: {}", username);
            }
        }

        chain.doFilter(request, response);
    }
}
