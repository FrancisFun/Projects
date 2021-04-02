package com.aiot.web.auth;

import com.aiot.web.auth.filter.JWTAuthenticationFilter;
import com.aiot.web.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author: Frank
 * @Date: 2021/2/26
 */

/**
 * 鉴权配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 需要放行的URL
     */
    public static final String[] AUTH_WHITELIST = {
            "/user/login",
            "/user/register",
            "/push/receive"
    };

    @Resource
    private DataSource dataSource;

    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true); // 启动创建表，创建成功后注释掉
        return tokenRepository;
    }

    @Bean
    UserDetailsService customUserService() { // 注册UserDetailsService 的bean
        return new UserDetailsServiceImpl();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置请求拦截
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                //由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //可以匿名访问的链接
                .antMatchers(AUTH_WHITELIST).permitAll()
                //其他所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                //.addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }
}
