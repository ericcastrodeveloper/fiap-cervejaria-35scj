package br.com.fiap.cervejaria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //noop - sem criptografia
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN", "USER")
                .and().withUser("user").password("{noop}user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST,  "/cervejas").hasRole("ADMIN")
            //csrf - disabled para usar postman
            .and().csrf().disable()
            .formLogin().disable();

    }
}
