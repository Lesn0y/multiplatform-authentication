package com.lesnoy.oidcservice.config;

import com.lesnoy.oidcservice.auth.AuthenticationFilter;
import com.lesnoy.oidcservice.auth.google.CustomerOAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class AuthConfiguration {

    private final AuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomerOAuthUserService oAuthUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/user/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                )
                .oauth2Login(Customizer.withDefaults())
//                    .loginPage("/login")
//                    .userInfoEndpoint()
//                    .userService(oAuthUserService)
//                .and().and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/api/v1")
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
