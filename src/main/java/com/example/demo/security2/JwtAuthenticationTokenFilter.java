//package com.example.demo.security2;
//
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import com.example.demo.entity.User2;
//import com.example.demo.service2.Jwt2Service;
//import com.example.demo.service2.User2Service;
//
//public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
//  private final static String TOKEN_HEADER = "authorization";
//  @Autowired
//  private Jwt2Service jwtService;
//  @Autowired
//  private User2Service userService;
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//      throws IOException, ServletException {
//    HttpServletRequest httpRequest = (HttpServletRequest) request;
//    String authToken = httpRequest.getHeader(TOKEN_HEADER);
//    if (jwtService.validateTokenLogin(authToken)) {
//      String username = jwtService.getUsernameFromToken(authToken);
//      User2 user = userService.loadUserByUsername(username);
//      if (user != null) {
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        UserDetails userDetail = new User(username, user.getPassword(), enabled, accountNonExpired,
//            credentialsNonExpired, accountNonLocked, user.getAuthorities());
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
//            null, userDetail.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//      }
//    }
//    chain.doFilter(request, response);
//  }
//}