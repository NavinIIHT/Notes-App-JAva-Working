package com.example.notesservice.config;

/*import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
*/
/*@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
         .passwordEncoder(encoder)
         .withUser("spring")
         .password(encoder.encode("secret"))
         .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
          .antMatchers("/noteservice/admin/**")
         .authenticated()
          .antMatchers("/noteservice/public/**")
          .permitAll()
          .and()
          .httpBasic();
    }

}*/
