package net.prabhat.todo_management.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {


    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth->{
//                    auth.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
//                    auth.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
//                    auth.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
//                    auth.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");
                   // auth.requestMatchers(HttpMethod.GET,"/api/**").permitAll(); this exposes out rest API to all
                })
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }



//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails prabhat = User.builder()
//                .username("prabhat")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(prabhat, admin);
//    }
}
