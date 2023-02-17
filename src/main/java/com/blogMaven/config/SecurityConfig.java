package com.blogMaven.config;

import com.blogMaven.config.auth.PricipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // security filter 설정
@EnableGlobalMethodSecurity(prePostEnabled = true) // 주소 접근 시 , 권한 및 인증 미리 체크
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {
    @Autowired
    private PricipalDetailService pricipalDetailService;
    @Bean
    public BCryptPasswordEncoder encoderPWD() {
        return new BCryptPasswordEncoder();
    }

    protected void cofigure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(pricipalDetailService).passwordEncoder(encoderPWD());
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http    .csrf().disable() // csrf 토큰 비활성화 - 테스트시 사용
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/" , "/auth/**" , "/js/**" , "/css/**" , "/image/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당주소로 요청하는 로그인을 가로채서 대신 로그인
                    .defaultSuccessUrl("/");

        return http.build();
    }
}
