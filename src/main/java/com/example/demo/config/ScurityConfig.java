package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ScurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        super.configure(httpSecurity);
        httpSecurity.authorizeRequests()
                .antMatchers("/")
                .permitAll()
         // .antMatchers("/api/user/*").hasRole(USER)
        // .antMatchers("/api/admin/*").hasRole(ADMIN)
       // .and()

        /*ENABLE AUTH*/
         .anyRequest() //zamist tego co na powyzej
         .authenticated() //zamist tego co powyzej
        .and() //nie potrzebne gdy gora odkomentowana
        .formLogin();

        httpSecurity.csrf().disable(); //odblokowuje POST request (403 forbidden)
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    .withUser("sa")
                    .password("{noop}sa")
                    .roles(USER)
                    .and()
                    .withUser("admin")
                    .password("{noop}admin")
                    .roles(USER, ADMIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
