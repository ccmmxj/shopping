package com.shop.server.config;

import com.shop.server.mapper.ManagePersiomMapper;
import com.shop.server.mapper.ManageSecurityMapper;
import com.shop.server.model.ManagePersiom;
import com.shop.server.model.ManageSecurity;
import com.shop.server.service.ManageUserService;
import com.shop.server.service.impl.UserServiceImpl;
import com.shop.server.utils.MD5PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ManageUserService manageUserService;
    @Autowired
    private ManageSecurityMapper eduSecurityMapper;
    @Autowired
    private ManagePersiomMapper eduPersiomMapper;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new MD5PasswordEncoder()); //user Details Service验证
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        LoginFailedHandler loginFailedHandler = new LoginFailedHandler();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        List<ManageSecurity> eduSecuritys = eduSecurityMapper.selectAll();
        eduSecuritys.stream().forEach((value) -> {
            List<ManagePersiom> eduPersioms = eduPersiomMapper.selectBySecurityId(value.getId());
            try {
                http.authorizeRequests().antMatchers(value.getUrl()).hasAnyAuthority(eduPersioms.stream().map(eduPersiom -> eduPersiom.getName()).collect(Collectors.toList()).toArray(new String[0]));
            } catch (Exception e) {
                logger.info("configure ---- {}",e.getMessage());
                e.printStackTrace();
            }
        });
        http.authorizeRequests()
//                .antMatchers("/manage/**").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailedHandler)
                .and().logout().permitAll()
                .and().csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
//        http.authorizeRequests()
//                .antMatchers("/**").authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .and().logout().permitAll()
//                .and().csrf().disable();
    }
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
////                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//        http.csrf().disable();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
}
