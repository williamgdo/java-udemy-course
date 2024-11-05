package net.javaguides.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSpringSecurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) ->
//                        liberar site inteiro:
                    authorize.anyRequest().authenticated()
//                authorize.requestMatchers(new AntPathRequestMatcher("/resources/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole("ADMIN", "GUEST")
                )
                .formLogin(form ->
                        form.loginPage("/login")
                                .defaultSuccessUrl("/admin/posts")
                                .loginProcessingUrl("/login")
                                .permitAll()
                );

        return http.build();
    }
}
