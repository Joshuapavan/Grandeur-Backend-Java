package com.Grandeur.GranduerBackend.security.configs;


import com.Grandeur.GranduerBackend.services.serviceImplementations.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfigs extends WebSecurityConfigurerAdapter {

    private final ClientServiceImpl clientService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Modifying the existing security provided by spring security //

        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/v*/registration/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin();


                .csrf()
                .disable()
                .authorizeRequests()

                // Authentication required to access this api //
                .antMatchers("/api/v*/registration/**")
                .permitAll()

                // Authentication required to access this api //
                .antMatchers("/api/v*/clients/**")
                .permitAll()

                // making sure that non-authenticated users can only consume data from cars api //
                .antMatchers("/api/v*/cars/**")
                .permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(clientService);

        return provider;
    }

}
