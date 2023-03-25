package pl.sdaacademy.conferenceroomreservationsystem.configuration;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.sdaacademy.conferenceroomreservationsystem.session.SessionFilter;
import pl.sdaacademy.conferenceroomreservationsystem.user.CurrentUserService;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories("pl.sdaacademy.conferenceroomreservationsystem.repository")
public class SecurityConfiguration {

    @Autowired
    private CurrentUserService userDetailsService;

    @Autowired
    private SessionFilter sessionFilter;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http = http.exceptionHandling().authenticationEntryPoint(
                ((request, response, authException) -> response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        authException.getMessage()
                ))
        ).and();


        http.authorizeHttpRequests()
                .requestMatchers("/api/login").permitAll()
                .anyRequest().authenticated();


        http.addFilterBefore(
                sessionFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
