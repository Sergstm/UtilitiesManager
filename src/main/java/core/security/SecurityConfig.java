package core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder encoder = PasswordEncoderFactories
            .createDelegatingPasswordEncoder();

    private final CustomUserDetailsService customUserDetailsService;
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/registration", "/addUser").permitAll()
                .antMatchers("/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/deny")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/addOrder")
                .permitAll()
                .and()
                .logout()
//                .logoutUrl("/logout")
                .permitAll()
//                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);
    }
}
