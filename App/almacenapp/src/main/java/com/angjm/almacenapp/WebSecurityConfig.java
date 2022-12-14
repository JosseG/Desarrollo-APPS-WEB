package com.angjm.almacenapp;

import com.angjm.almacenapp.services.user.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyUserDetailsService userDet;


    private BCryptPasswordEncoder bCryptPassEncoder;

    @Autowired
    private final AuthenticationConfiguration configuration;



    public BCryptPasswordEncoder passwordEncoder(){
        bCryptPassEncoder = new BCryptPasswordEncoder(4);
        return bCryptPassEncoder;
    }
    String[] resources = new String[]{
            "/include/**","/css/**","/customcss/**","/icons/**","/img/**","/js/**","/layer/**","/componentes/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/inventario/orden/genera/**").hasAnyRole("ADMINISTRADOR","VENDEDOR")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/homep",true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .and()
                .rememberMe().
                    tokenRepository(persistentTokenRepository())
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        return http.build();
    }


    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepo = new JdbcTokenRepositoryImpl();
        tokenRepo.setDataSource(dataSource);
        return tokenRepo;
    }

    @Autowired
    void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDet).passwordEncoder(passwordEncoder());
    }


}
