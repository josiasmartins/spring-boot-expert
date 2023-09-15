package io.github.josiasmartins.config;

import io.github.josiasmartins.security.jwt.JwtAuthFilter;
import io.github.josiasmartins.security.jwt.JwtService;
import io.github.josiasmartins.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity // implementa o @Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // $2a$12$IFBpq3I3jec4FdfwTDzmGOCdGCCuySnCzKZbeElUFebvdHtD192v2
    // $2a$12$UhyS80bl0Eszdj4st9m/R.TpSNJI2c/hRSKna5t2VXd48zizuToPu

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder(); // descryptografa e cryptografa
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioServiceImpl);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(usuarioServiceImpl)
                .passwordEncoder(passwordEncoder());

//        super.configure(auth);
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("fulano")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf: permite que aja um secuguranca entre uma aplicacao web e back
                .authorizeRequests()
                    .antMatchers("/api/clientes/**")
    //                        .hasAnyAuthority("MANTER_USUARIO");
    //                    .authenticated()
    //                    .permitAll()
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")

                    .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                        .permitAll()
                    .anyRequest().authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // sem sessão
                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**");
    }

    /**
     * <form method="post">
     *     <input type="text" name="username">
     *     <input type="secret" name="password">
     *     <button type="submit"></button>
     * </form>
     */

}
