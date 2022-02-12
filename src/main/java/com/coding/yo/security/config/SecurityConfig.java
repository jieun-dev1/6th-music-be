package com.coding.yo.security.config;

import com.coding.yo.security.filter.JwtFilter;
import com.coding.yo.security.service.UserDetailsServiceImpl;
import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final FirebaseAuth firebaseAuth;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //CSRF 보호기능 disable
                .authorizeRequests() //요청에 대한 권한 지정
                .anyRequest().authenticated() //모든 요청이 인증되어야 한다.
                .and()
                .addFilterBefore(new JwtFilter(userDetailsServiceImpl, firebaseAuth), UsernamePasswordAuthenticationFilter.class);
    }

    ;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/users")
                .antMatchers("/")
                .antMatchers("/resources/**");
//                .antMatchers("/users/**");

    }

}
