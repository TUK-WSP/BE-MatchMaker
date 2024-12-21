package org.wsp.matchmaker.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 비활성화 (REST API의 경우 일반적)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/**") // /api/** 경로에 대해 CSRF 보호 비활성화
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() //** 경로는 모든 사용자에게 허용
                        .requestMatchers("/admin/**").authenticated() // /admin/** 경로는 인증된 사용자에게만 허용
                        .anyRequest().permitAll() // 그 외의 모든 요청은 허용
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                );

        return http.build();
    }
}
