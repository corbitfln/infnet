package br.infnet.edu.requerimentoCompensacao.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("rogerio")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .authorities("ROLE_ADMIN")
            .and()
                .withUser("ribeiro")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .authorities("ROLE_USER")
            .and()
                .passwordEncoder( new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/requerimentos-pensao/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
            .and()
                .httpBasic()
            .and().csrf().disable();
    }
}
