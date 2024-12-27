package com.main.config;

import com.main.service.UserDetaitSer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final UserDetaitSer userDetaitSer;

    public SecurityConfig(UserDetaitSer userDetaitSer) {
        this.userDetaitSer = userDetaitSer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Default password encoder
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.csrf(c->c.disable()).cors(c->c.disable());
//     httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/26/create").permitAll()
//             .anyRequest().authenticated());
//     httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.authorizeHttpRequests(auth-> auth.anyRequest().permitAll());
    return httpSecurity.build();
}

@Bean
public AuthenticationManager authManger(HttpSecurity httpSecurity)throws Exception{
            AuthenticationManagerBuilder authenticationManagerBuilder =
                    httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
            authenticationManagerBuilder.userDetailsService(userDetaitSer)
                    .passwordEncoder(new BCryptPasswordEncoder());
            return authenticationManagerBuilder.build();
        }
}
