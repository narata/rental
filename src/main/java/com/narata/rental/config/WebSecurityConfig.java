package com.narata.rental.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narata.rental.bean.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * @author narata
 * @since 2019/03/26
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailService userDetailService;

    @Autowired
    public WebSecurityConfig(MyUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello", "/login_j", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login")
                .loginPage("/not_login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    RespBean respBean;
                    if (e instanceof BadCredentialsException ||
                            e instanceof UsernameNotFoundException) {
                        respBean = RespBean.error("账户名或者密码输入错误!");
                    } else if (e instanceof LockedException) {
                        respBean = RespBean.error("账户被锁定，请联系管理员!");
                    } else if (e instanceof CredentialsExpiredException) {
                        respBean = RespBean.error("密码过期，请联系管理员!");
                    } else if (e instanceof AccountExpiredException) {
                        respBean = RespBean.error("账户过期，请联系管理员!");
                    } else if (e instanceof DisabledException) {
                        respBean = RespBean.error("账户被禁用，请联系管理员!");
                    } else {
                        respBean = RespBean.error("登录失败!");
                    }
                    resp.setStatus(401);
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(respBean));
                    out.flush();
                    out.close();
                })
                .successHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    RespBean respBean = RespBean.ok("登录成功!");
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(respBean));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    RespBean respBean = RespBean.ok("注销成功!");
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(respBean));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .cors().and()
                .csrf().disable();
        http.addFilterAt(customAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler((req, resp, authentication) -> {
            resp.setContentType("application/json;charset=utf-8");
            RespBean respBean = RespBean.ok("登录成功!");
            ObjectMapper om = new ObjectMapper();
            PrintWriter out = resp.getWriter();
            out.write(om.writeValueAsString(respBean));
            out.flush();
            out.close();
        });
        filter.setAuthenticationFailureHandler((req, resp, e) -> {
            resp.setContentType("application/json;charset=utf-8");
            RespBean respBean = null;
            if (e instanceof BadCredentialsException ||
                    e instanceof UsernameNotFoundException) {
                respBean = RespBean.error("账户名或者密码输入错误!");
            } else if (e instanceof LockedException) {
                respBean = RespBean.error("账户被锁定，请联系管理员!");
            } else if (e instanceof CredentialsExpiredException) {
                respBean = RespBean.error("密码过期，请联系管理员!");
            } else if (e instanceof AccountExpiredException) {
                respBean = RespBean.error("账户过期，请联系管理员!");
            } else if (e instanceof DisabledException) {
                respBean = RespBean.error("账户被禁用，请联系管理员!");
            } else {
                respBean = RespBean.error("登录失败!");
            }
            resp.setStatus(401);
            ObjectMapper om = new ObjectMapper();
            PrintWriter out = resp.getWriter();
            out.write(om.writeValueAsString(respBean));
            out.flush();
            out.close();
        });
        filter.setFilterProcessesUrl("/login_j");

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
