//package com.example.demo.security2;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//  @Bean
//  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
//    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
//    jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
//    return jwtAuthenticationTokenFilter;
//  }
//  @Bean
//  public RestAuthenticationEntryPoint restServicesEntryPoint() {
//    return new RestAuthenticationEntryPoint();
//  }
//  @Bean
//  public CustomAccessDeniedHandler customAccessDeniedHandler() {
//    return new CustomAccessDeniedHandler();
//  }
//  @Bean
//  @Override
//  protected AuthenticationManager authenticationManager() throws Exception {
//    return super.authenticationManager();
//  }
//  protected void configure(HttpSecurity http) throws Exception {
//    // Disable crsf cho đường dẫn /rest/**
//    http.csrf().ignoringAntMatchers("/rest2/**");
//    http.authorizeRequests().antMatchers("/rest2/login**").permitAll();
//    http.antMatcher("/rest2/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//        .antMatchers(HttpMethod.GET, "/rest2/**").access("hasRole('ADMIN') or hasRole('BROKER')")
//        .antMatchers(HttpMethod.POST, "/rest2/**").access("hasRole('ADMIN')")
//        .antMatchers(HttpMethod.DELETE, "/rest2/**").access("hasRole('ADMIN')").and()
//        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
//  }
//}