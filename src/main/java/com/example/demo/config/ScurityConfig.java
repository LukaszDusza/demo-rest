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
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
              //  .anyRequest()
              //  .authenticated()
             //   .and()
             //   .httpBasic();


             //   .antMatchers( "/**").permitAll()
             //   .antMatchers("/api/**").hasRole(ADMIN)
             //   .anyRequest()
             //   .authenticated()
              //  .antMatchers("/api/**")
               // .hasRole(ADMIN)
              //  .antMatchers("/")
             //   .permitAll()
             //   .antMatchers("/api").hasRole(ADMIN)
        // .antMatchers("/api/admin/*").hasRole(ADMIN)
       // .and()

        /*ENABLE AUTH*/
       //  .anyRequest() //zamist tego co na powyzej
      //   .authenticated() //zamist tego co powyzej
      //  .and() //nie potrzebne gdy gora odkomentowana

       // .and()
       // .logout();

     //   httpSecurity.csrf().disable(); //odblokowuje POST request (403 forbidden)
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    .withUser("sa")
                    .password("{noop}sa")
                    .roles(USER,ADMIN)
                    .and()
                    .withUser("user")
                    .password("{noop}user")
                    .roles(USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
