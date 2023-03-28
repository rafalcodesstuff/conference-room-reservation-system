package pl.sdaacademy.conferenceroomreservationsystem.configuration;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.sdaacademy.conferenceroomreservationsystem.session.SessionFilter;
import pl.sdaacademy.conferenceroomreservationsystem.user.CurrentUserService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories("pl.sdaacademy.conferenceroomreservationsystem.repository")
public class SecurityConfiguration {

    @Autowired // injection through constructor raises an error
    private CurrentUserService userDetailsService;

    @Autowired // injection through constructor raises an error
    private SessionFilter sessionFilter;


    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager testAdmin = new InMemoryUserDetailsManager();
        testAdmin.createUser(User.withDefaultPasswordEncoder()
                .username("TestAdmin")
                .password("admin")
                .roles("ADMIN")
                .build());
        return testAdmin;
    }

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

        // for testing
//        http.authorizeHttpRequests().requestMatchers("/**").hasRole("ADMIN");


        http = http.exceptionHandling().authenticationEntryPoint(
                ((request, response, authException) -> response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        authException.getMessage()
                ))
        ).and();

        http.authorizeHttpRequests()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers("/api/register").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(
                sessionFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsCfg = new CorsConfiguration().applyPermitDefaultValues();
        corsCfg.addAllowedMethod(HttpMethod.DELETE); // need this to delete calendar reservationss

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", corsCfg);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
