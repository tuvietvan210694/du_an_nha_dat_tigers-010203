package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
    jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
    return jwtAuthenticationTokenFilter;
  }
  @Bean
  public RestAuthenticationEntryPoint restServicesEntryPoint() {
    return new RestAuthenticationEntryPoint();
  }
  @Bean
  public CustomAccessDeniedHandler customAccessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }
  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
  protected void configure(HttpSecurity http) throws Exception {
    // Disable crsf cho đường dẫn /rest/**
    http.csrf().ignoringAntMatchers("/admin/**");
    http.csrf().disable();
    
    
    //web.ignoring().antMatchers(HttpMethod.POST, "/auth/**");
    http.authorizeRequests().antMatchers("/rest/login**").permitAll();  
    
    
    //http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN").and().authorizeRequests().anyRequest().authenticated();

    //http.authorizeRequests().antMatchers("/broker/**").hasAuthority("ROLE_BROKER").and().authorizeRequests().anyRequest().authenticated();
    
    
	
//    http.authorizeRequests().anyRequest().authenticated();
//http.authorizeRequests().and().formLogin()
//		//.loginPage("/login").permitAll().usernameParameter("email").passwordParameter("password")
//         .loginPage("/login").usernameParameter("email").passwordParameter("password")
//		.loginProcessingUrl("/login").successHandler(successLoginHandle).failureHandler(failLoginHandle)
//		// setting remember me
//		.and().rememberMe().rememberMeParameter("remember-me")
//		// setting logout
//		.and().logout().logoutUrl("/logout").permitAll()
//		// delete cookies when logout
//		//.logoutSuccessUrl("/login?logout").permitAll().and()
//		.deleteCookies("JSESSIONID", "remember-me").logoutSuccessUrl("/login?logout").permitAll().and()
//		.httpBasic();
    
    
    
    
//    http.antMatcher("/a/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
//    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//    .antMatchers(HttpMethod.GET, "/a/broker/**").access("hasRole('ROLE_BROKER')")
//    .antMatchers(HttpMethod.POST, "/a/broker/**").access("hasRole('ROLE_BROKER')")
//    .antMatchers(HttpMethod.DELETE, "/a/broker/**").access("hasRole('ROLE_BROKER')").and()
//    .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//    .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    
    http.antMatcher("/a2/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
    .antMatchers(HttpMethod.GET, "/a2/broker/**").access("hasRole('ROLE_BROKER')")
    .antMatchers(HttpMethod.DELETE, "/a2/broker/**").access("hasRole('ROLE_BROKER')")
    .antMatchers(HttpMethod.POST, "/a2/broker/**").access("hasRole('ROLE_BROKER')").and()
    .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
    .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
     
//    http.authorizeRequests()
//	.antMatchers("/forget-password**", "/h2-console/**", "/login**", "/register**",
//			"/activeAccount**", "/change-password**")
//	.permitAll();
      
//    
//  http.authorizeRequests()
//	.antMatchers("rest/login/**")
//	.permitAll();
//    
//    
//        http.authorizeRequests()
//        .antMatchers(HttpMethod.GET, "/a/broker/**").hasRole("BROKER")
//        .antMatchers(HttpMethod.POST, "/a/broker/**").hasRole("BROKER")
//        .antMatchers(HttpMethod.DELETE, "/a/broker/**").hasRole("BROKER").and()
//        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler()); 
//        
        
        http.antMatcher("/a2/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/a2/admin/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.POST, "/a2/admin/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/a2/admin/**").access("hasRole('ROLE_ADMIN')").and()
        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler()).and().rememberMe().rememberMeParameter("remember-me")
        .and().logout().logoutUrl("/api/logout").permitAll().deleteCookies("JSESSIONUI", "remember-me").logoutSuccessUrl("/login?logout").permitAll().and().httpBasic();
        
        
//        .and().rememberMe().rememberMeParameter("remember-me")
//		// setting logout
//		.and().logout().logoutUrl("/logout").permitAll()
//		// delete cookies when logout
//		//.logoutSuccessUrl("/login?logout").permitAll().and()
//		.deleteCookies("JSESSIONID", "remember-me").logoutSuccessUrl("/login?logout").permitAll().and()
//		.httpBasic();
    
    
    
    
  }
}
