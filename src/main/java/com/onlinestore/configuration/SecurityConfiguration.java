package com.onlinestore.configuration;

import com.onlinestore.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public SecurityConfiguration(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userServiceImplementation)  // serviciul care identifica daca utilizatorul cu un anumit username exista in DB
                .passwordEncoder(new BCryptPasswordEncoder()); // serivicul care identifica daca password-ul unui user este valid.
    }


    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "*").permitAll()
                .antMatchers("/users/**").permitAll() // Permitem accesul la acest API fara ca utilizatorul sa fie log
                .antMatchers(HttpMethod.POST, "/categories/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/products/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/categories/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/categories/**").hasAnyAuthority("ADMIN")
                .anyRequest() // Pentru orice alt request verificam daca userul este logat.
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .cors()
                .disable();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("POST", "OPTIONS", "DELETE", "PUT", "GET");

            }
        };
    }
}
