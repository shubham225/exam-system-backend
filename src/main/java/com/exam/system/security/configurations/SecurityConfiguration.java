package com.exam.system.security.configurations;


import com.exam.system.security.configurations.exceptionhandler.MyAccessDeniedHandler;
import com.exam.system.security.configurations.exceptionhandler.MyAuthenticationEntryPoint;
import com.exam.system.security.configurations.filters.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    private final MyAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthFilter jwtAuthFilter;
    SecurityConfiguration(MyAuthenticationEntryPoint myAuthenticationEntryPoint,
                          MyAccessDeniedHandler accessDeniedHandler,
                          JwtAuthFilter jwtAuthFilter) {
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/V1/auth/register", "/api/V1/auth/login").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/V1/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(this.myAuthenticationEntryPoint))
                .exceptionHandling((exception) -> exception.accessDeniedHandler(this.accessDeniedHandler))
                .addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class)
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}