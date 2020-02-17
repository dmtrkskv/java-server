package myapp.main.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import myapp.main.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // временно отключил csrf, чтобы работали POST-запросы
        http.csrf().disable().authorizeRequests().antMatchers("/createUser").permitAll().anyRequest().authenticated().and()
                .formLogin();
    }

    @Autowired
    protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}