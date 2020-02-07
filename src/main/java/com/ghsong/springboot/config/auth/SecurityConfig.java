package com.ghsong.springboot.config.auth;

import com.ghsong.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화 시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // h2-console 화면을 사용하기 위해 해당 옵션들을 disable.
                .headers().frameOptions().disable()
                .and()
                    // URL 별 권한 관리를 설정하는 옵션의 시작점. authorizeRequests가 선언되어야만 antMatchers 옵션 사용 가능
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()      // 권한 관리 대상 지정 옵션. 지정된 URL은 전체 열람 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())                                    // 해당 API는 USER 권한을 가진 사용자만 열람 가능
                    .anyRequest().authenticated()                                                                       // 설정된 값들 이외 나머지 URL은 인증된 사용자들에게만 허용, 즉 로그인 사용자
                .and()
                    .logout()
                        .logoutSuccessUrl("/")                                                   // 로그아웃 성공 시 '/' 주소로 이동
                .and()
                    .oauth2Login()                                                               // OAuth 2 로그인 기능에 대한 여러 설정 진입점
                        .userInfoEndpoint()                                                      // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정
                            // 소셜 로그인 설공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                            // 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
                            .userService(customOAuth2UserService);
    }
}
