package com.codegym.casestudy.config;


import com.codegym.casestudy.service.account.AccountDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountDetailsServiceImpl accountDetailsService;
    @Autowired
    private DataSource dataSource;

    public BCryptPasswordEncoder passwordEncoder() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(accountDetailsService).passwordEncoder(passwordEncoder());

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/account/signup", "/account/login", "/index", "/account/logoutSuccessful").permitAll();

        http.authorizeRequests().antMatchers("/account").authenticated();
        // các trang cần login
        http.authorizeRequests().antMatchers("/success").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/success/").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN', 'ROLE_TEACHER')");
        // account
        http.authorizeRequests().antMatchers("/account").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/account/").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/account/delete").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/account/add").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/account/change-password").permitAll();
        http.authorizeRequests().antMatchers("/account/forgot").permitAll();
        http.authorizeRequests().antMatchers("/account/create").permitAll();
        http.authorizeRequests().antMatchers("/account/confirm").permitAll();
        http.authorizeRequests().antMatchers("/account/confirm-code").permitAll();
        //teacher
        http.authorizeRequests().antMatchers("/teacher").access("hasAnyRole('ROLE_AMDIN','ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teacher/").access("hasAnyRole('ROLE_AMDIN','ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teacher/teacherList").access("hasAnyRole('ROLE_AMDIN','ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teacher/add").access("hasAnyRole('ROLE_AMDIN')");
        http.authorizeRequests().antMatchers("/teacher/add").access("hasAnyRole('ROLE_AMDIN')");
        http.authorizeRequests().antMatchers("/teacher/edit/{id}/").access("hasAnyRole('ROLE_AMDIN','ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teacher/edit").access("hasAnyRole('ROLE_AMDIN','ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teacher/edit/").access("hasAnyRole('ROLE_AMDIN','ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/teacher/delete").access("hasAnyRole('ROLE_AMDIN')");
        //student
        http.authorizeRequests().antMatchers("/student").access("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT','ROLE_TEACHER')");
        http.authorizeRequests().antMatchers("/student/").access("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT','ROLE_TEACHER')");

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/account/login")//
                .defaultSuccessUrl("/success?msg=true")//
                .failureUrl("/account/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/account/logout").logoutSuccessUrl("/account/logoutSuccessful").permitAll();

        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }

    public LogoutHandler logoutHandler() {
        return new LogoutHandlerImpl();
    }
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

}
