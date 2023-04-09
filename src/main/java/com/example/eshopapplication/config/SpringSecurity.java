package com.example.eshopapplication.config;

import com.example.eshopapplication.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurity {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests(
//                        (authorize) ->
//                        authorize
//                                .mvcMatchers("/users").hasAuthority("ADMIN")
//                                .mvcMatchers("/asdasdasd").hasAuthority("ADMIN")
//                                .mvcMatchers("/products/**").hasAuthority("ADMIN")
//                                .mvcMatchers("/users", "").hasRole("ROLE_USER")
//                                .mvcMatchers("/register", "/login").permitAll()
//                                .mvcMatchers("/logout").hasAuthority("ADMIN")
//                                .mvcMatchers("/index").permitAll()
//                )
//                .exceptionHandling()
//                .accessDeniedPage("/accessDenied")
//                .and()
//                .formLogin(
//                        form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/")
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                );
//        return http.build();
//    }
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .exceptionHandling()
            .accessDeniedPage("/accessDenied")
            .and()
            .formLogin(
                    form -> form
                            .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/")
                            .permitAll()
            ).logout(
                    logout -> logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .permitAll()
            )
            .authorizeHttpRequests(
                    (authorize) ->
                            authorize
//                                    .mvcMatchers("/users").hasAuthority("ADMIN")
                                    .mvcMatchers("/asdasdasd").hasAuthority("ADMIN")
//                                    .mvcMatchers("/products/**").hasAuthority("ADMIN")
//                                    .mvcMatchers("/users", "").hasRole("ROLE_USER")
//                                    .mvcMatchers("/register", "/login").permitAll()
//                                    .mvcMatchers("/logout").hasAuthority("ADMIN")
//                                    .mvcMatchers("/index").permitAll()
            );
    return http.build();
}
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
