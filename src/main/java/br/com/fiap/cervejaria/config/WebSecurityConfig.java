package br.com.fiap.cervejaria.config;

import br.com.fiap.cervejaria.security.JwtAuthenticationEntryPoint;
import br.com.fiap.cervejaria.security.JwtRequestFilter;
import br.com.fiap.cervejaria.security.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(JwtUserDetailsService jwtUserDetailsService, JwtRequestFilter jwtRequestFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, PasswordEncoder passwordEncoder){
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //noop - sem criptografia
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN", "USER")
//                .and().withUser("user").password("{noop}user").roles("USER");
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().and().authorizeRequests()
//            .antMatchers(HttpMethod.POST,  "/cervejas").hasRole("ADMIN")
            //todas requests de users estão permitidas
            .antMatchers("/users/**").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/v2/api-docs").permitAll()
            //autenticar qualquer requisição
            .anyRequest().authenticated()
            .and()
            //se alguma exceção for chamada lança 401
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            //sem estado para caso a caia em outro servidor não ficar autenticando toda vez que caia em outro servidor
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //csrf - disabled para usar postman
            .and().csrf().disable()
            .formLogin().disable();

        //jwtReqFilter -> userPassAuthFitler
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
