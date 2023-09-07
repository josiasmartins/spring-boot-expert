package io.github.josiasmartins.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // implementa o @Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // $2a$12$IFBpq3I3jec4FdfwTDzmGOCdGCCuySnCzKZbeElUFebvdHtD192v2
    // $2a$12$UhyS80bl0Eszdj4st9m/R.TpSNJI2c/hRSKna5t2VXd48zizuToPu
    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("fulano")
                .password(passwordEncoder().encode("123"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf: permite que aja um secuguranca entre uma aplicacao web e back
                .authorizeRequests()
                    .antMatchers("/api/clientes/**")
//                        .hasAnyAuthority("MANTER_USUARIO");
//                    .authenticated()
                    .permitAll()
                .and()
                    .formLogin();

    }

    /**
     * <form method="post">
     *     <input type="text" name="username">
     *     <input type="secret" name="password">
     *     <button type="submit"></button>
     * </form>
     */

}
