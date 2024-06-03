package com.exam.system.security.configurations;


import com.exam.system.security.configurations.exceptionhandler.MyAccessDeniedHandler;
import com.exam.system.security.configurations.exceptionhandler.MyAuthenticationEntryPoint;
import com.exam.system.security.configurations.exceptionhandler.MyBearerTokenAuthenticationEntryPoint;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    private final MyBearerTokenAuthenticationEntryPoint myBearerTokenAuthenticationEntryPoint;
    private final MyAccessDeniedHandler accessDeniedHandler;
    private final RsaKeyProperties rsaKeys;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${frontend.url}")
    private String frontendUrl;

    SecurityConfiguration(MyAuthenticationEntryPoint myAuthenticationEntryPoint, MyBearerTokenAuthenticationEntryPoint myBearerTokenAuthenticationEntryPoint,
                          MyAccessDeniedHandler accessDeniedHandler,
                          RsaKeyProperties rsaKeys, BCryptPasswordEncoder bCryptPasswordEncoder, BCryptPasswordEncoder bCryptPasswordEncoder1) {
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.myBearerTokenAuthenticationEntryPoint = myBearerTokenAuthenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.rsaKeys = rsaKeys;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder1;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/V1/auth/login").permitAll()
                        .requestMatchers("/api/V1/auth/register").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/V1/user/**").hasAnyAuthority("SCOPE_ROLE_ADMIN")
                        .requestMatchers("/api/V1/admin/**").hasAnyAuthority("SCOPE_ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(this.myBearerTokenAuthenticationEntryPoint)
                        .accessDeniedHandler(this.accessDeniedHandler)
                )
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(this.myAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList(frontendUrl));
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT
                ));
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()
        ));
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(-102);
        return bean;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        RSAKey rsaKey = new RSAKey.Builder(rsaKeys.getPublicKey())
                .privateKey(rsaKeys.getPrivateKey())
                .keyID(rsaKeys.getKeyId())
                .build();

        JWKSet jwkSet = new JWKSet(rsaKey);

        return new NimbusJwtEncoder(new ImmutableJWKSet<>(jwkSet));
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(bCryptPasswordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(authenticationProvider);
    }
}