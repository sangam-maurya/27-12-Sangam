package com.main.config;

import com.main.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {
    private final UserDetailService userDetaitSer;

    public SecurityConfig(UserDetailService userDetaitSer) {
        this.userDetaitSer = userDetaitSer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Default password encoder
    }
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the default "ROLE_" prefix
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.csrf(c->c.disable()).cors(c->c.disable());
//       httpSecurity.authorizeHttpRequests(auth-> auth.requestMatchers("/api/v1/26/create", "/api/v1/26/login").permitAll()
//               .requestMatchers("/api/v1/26/get").hasAuthority("USER")
//               .requestMatchers("/api/v1/26/ID").hasAuthority("ADMIN")
//               .requestMatchers("/api/v1/26/update").hasAuthority("ADMIN")
//               .requestMatchers("/api/v1/26/delete").hasAuthority("ADMIN")
//               .requestMatchers("/api/v1/26/pagination").hasAuthority("USER")
//               .requestMatchers("/api/v1//26/get").permitAll()
//               .anyRequest().authenticated());
//     httpSecurity.httpBasic(Customizer.withDefaults());
//

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
