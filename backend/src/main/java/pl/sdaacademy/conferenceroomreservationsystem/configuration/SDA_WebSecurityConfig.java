package pl.sdaacademy.conferenceroomreservationsystem.configuration;

//@Configuration
//@EnableWebSecurity
public class SDA_WebSecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors();
//        http.authorizeHttpRequests()
//                .requestMatchers("/**").permitAll()
////                .requestMatchers(HttpMethod.POST,"/organizations/").hasAuthority("ADMIN")
////                .requestMatchers(HttpMethod.GET,"/organizations/").hasAnyAuthority("ADMIN", "USER", "LEADER")
//                .anyRequest().authenticated()
//                .and().csrf().disable()
//                .httpBasic();
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User
//                .withUsername("user")
//                .password("{noop}password")
//                .roles("USER_ROLE")
//                .build();
//        UserDetails admin = User
//                .withUsername("admin")
//                .password("{noop}admin")
//                .roles("ADMIN_ROLE")
//                .build();
//        UserDetails organizationLeader = User
//                .withUsername("leader")
//                .password("{noop}password")
//                .roles("LEADER_ROLE")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin, organizationLeader);
//    }
//    @Bean
//    SecurityFilterChain web(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/resources/**", "/signup", "/about").permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/db/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') and hasRole('DBA')"))
//                // .requestMatchers("/db/**").access(AuthorizationManagers.allOf(AuthorityAuthorizationManager.hasRole("ADMIN"), AuthorityAuthorizationManager.hasRole("DBA")))
//                .anyRequest().denyAll()
//        );
//
//        return http.build();

//    }
//
//           @Bean
//           public PasswordEncoder passwordEncoder() {
//               return new BCryptPasswordEncoder(8);
//           }

}

