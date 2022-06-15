package pl.sb.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.sb.security.model.Role;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/styles.css").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasAuthority(Role.ROLE_ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .and()
                .headers().frameOptions().sameOrigin();
        http.formLogin().loginPage("/login").permitAll();
        http.logout().logoutSuccessUrl("/login");
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                .logoutSuccessUrl("/login?logout").permitAll()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}