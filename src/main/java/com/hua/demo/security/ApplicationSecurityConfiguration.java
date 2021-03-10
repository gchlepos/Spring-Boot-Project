package com.hua.demo.security;

import com.hua.demo.principals.UserPrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserAuthenticationHandler successHandler;

    @Bean
    public UserAuthenticationHandler successHandler() {
        return new UserAuthenticationHandler();
    };

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    private UserPrincipalDetailsService citizenPrincipalDetailsService;
    //private EmployeePrincipalDetailsService employeePrincipalDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder,
                                     UserPrincipalDetailsService citizenPrincipalDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.citizenPrincipalDetailsService = citizenPrincipalDetailsService;
        //this.employeePrincipalDetailsService = employeePrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    //method that secures things
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() //we want to authorize requests
                .antMatchers("/registration", "/login").anonymous()
                .antMatchers("/api/employee/**").hasRole(ApplicationUserRole.EMPLOYEE.name())
                .antMatchers("/api/citizen/**").hasRole(ApplicationUserRole.CITIZEN.name())
                .antMatchers("/api/admin/**").hasRole(ApplicationUserRole.ADMIN.name())
                .anyRequest() //any request
                .authenticated() //any request must be authenticated
                .and()
                .formLogin()
                .successHandler(successHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.citizenPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

}

