package com.coding.yo.security.config;

import com.coding.yo.security.filter.LocalJwtFilter;
import com.coding.yo.security.service.UserDetailsServiceImpl;
import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true) //추후 메소드 수준 권한 부여 용
public class LocalSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     *
     * @EnableWebSecurity 는 csrf 공격 방지 기능 적용 (자동 활성화)
     * rest API 이용하는 서버는 SESSION 기반 인증과 달리, STATELESS 하지 않아서 따로 서버에 인증 정보를 보관하지 않음.
     * CSRF 공격으로 부터 안전하기에 매번 API 요청에 있는 CSRF 토큰을 받지 않음.
     * 따라서 비활성화. csrf().disable() 로 적용.
     */

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //CSRF 보호기능 disable
                .authorizeRequests() //요청에 대한 권한 지정
                .anyRequest().authenticated() //모든 요청이 인증되어야 한다.
                .and()
                .addFilterBefore(new LocalJwtFilter(userDetailsServiceImpl), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    /**
     * 회원 가입 시, filter 를 거치지 않는다. UserDetails user = userDetailsService.loadUserByUsername(decodedToken.getUid())여기서 exception 이 터져서 controller 까지 넘어가지 못함.
     * (1)  get 로그인 요청을 보내서 200이면 / 로 라우팅 (2) 해당 에러일 경우 (404)이면 post 요청으로 routing
     * @param web
     * @throws Exception
     */

    @Override
    public void configure(WebSecurity web) throws Exception {
        //인증 예외 url 설정 (ex. 유저가 없을 경우 회원가입 POST/users 요청은 필터를 거치지 않는다)
        web.ignoring().antMatchers(HttpMethod.POST, "/users")
                .antMatchers(HttpMethod.POST, "/users/local")
                .antMatchers("/")
                .antMatchers("/resources/**");

    }

}
