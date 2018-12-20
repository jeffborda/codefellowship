package com.jeffborda.codefellowship.security;// TODO: put your package name here

// import com.ferreirae.securedemo.appuser.UserDetailsServiceImpl;
import com.jeffborda.codefellowship.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // We'll comment in these lines tomorrow, when we add a UserDetailsServiceImpl!
    // @Autowired
    // private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                    //.antMatchers("/admin/**").hasRole("ADMIN")
                    //.antMatchers("/anonymous*").anonymous()
                    .antMatchers("/style.css").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login*").permitAll()
                    .antMatchers("/signup*").permitAll()
                    .antMatchers("/users/newuser").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login.html")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/myprofile", true)
                //maybe make a bad login page with another login form
                    .failureUrl("/login")
                    //.failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                    .logoutUrl("/perform_logout")
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
























//package com.jeffborda.codefellowship.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/anonymous*").anonymous()
//                    .antMatchers("/login*").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/login.html")
//                    .loginProcessingUrl("/perform_login")
//                    .defaultSuccessUrl("/homepage.html", true)
//                    .failureUrl("/login.html?error=true")
//                    /*.failureHandler(authenticationFailureHandler())*/
//                .and()
//                    .logout()
//                    .logoutUrl("/perform_logout")
//                    .deleteCookies("JSESSIONID")
//                    /*.logoutSuccessHandler(logoutSuccessHandler())*/;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
