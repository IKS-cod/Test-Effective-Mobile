package com.effective_mobile.config;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Конфигурация безопасности веб-приложения. Этот класс настраивает механизмы безопасности, включая аутентификацию и авторизацию.
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Фильтр запросов JWT, используемый для проверки токенов аутентификации.
     */
    private final JwtRequestFilter jwtRequestFilter;

    /**
     * Массив путей, которые не требуют аутентификации (белый список).
     */
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/v3/api-docs/**", // Если используете SpringDoc
            "/swagger-ui/**", // Это для Swagger UI
            "/login",
            "/register"
    };

    /**
     * Конструктор для инициализации фильтра JWT.
     *
     * @param jwtRequestFilter фильтр запросов JWT
     */
    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * Бин для менеджера аутентификации. Этот метод создаёт и возвращает экземпляр менеджера аутентификации.
     *
     * @return менеджер аутентификации
     * @throws Exception если возникает ошибка при создании менеджера аутентификации
     */
    @Bean
    @Override
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Метод для настройки безопасности HTTP. Этот метод конфигурирует правила доступа и добавляет фильтр JWT.
     *
     * @param http конфигурация безопасности HTTP
     * @throws Exception если возникает ошибка при настройке безопасности
     */
    @Override
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Бин для кодировщика паролей. Этот метод возвращает экземпляр кодировщика паролей BCrypt.
     *
     * @return кодировщик паролей
     */
    @Bean
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

