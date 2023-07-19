package umunchat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CustomEntryPoint customEntryPoint;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeHttpRequests()
//                .antMatchers("/unsecure").permitAll()
//                .antMatchers("/user/create").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                        .oauth2Login().and().httpBasic();
//        http.oauth2Login().userInfoEndpoint().userService()
        System.out.println("security config run..");

        http
                .authorizeRequests()
                .antMatchers("/unsecure").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/secure").authenticated()
                .antMatchers("/login").authenticated()
                .anyRequest().authenticated() // Allow public access to other endpoints
                .and()
                .httpBasic()
//                .and()
//                .oauth2Login();

//                .userInfoEndpoint()
//                .userService(customOAuth2UserService())
//                // Custom user service
                ; // Enable Basic Authentication




        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.exceptionHandling().authenticationEntryPoint(customEntryPoint).and().authenticationProvider(customAuthenticationProvider);
//        http.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       System.out.println("configure hua ....");
        auth.eraseCredentials(false);
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



}






