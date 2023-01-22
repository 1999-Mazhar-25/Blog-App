package com.mazhar.blogs.app.config;

import com.mazhar.blogs.app.utils.JwtTokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

       String requestToken = request.getHeader("Authorization");
       String token = null;
       String username = null;

       if (requestToken != null && requestToken.startsWith("Bearer"))
       {
           token = requestToken.substring(7);
           try {
               username = jwtTokenHelper.getUsernameFromToken(token);
           } catch (IllegalArgumentException e) {
               System.out.println("Unable to get Jwt Token");
           }
           catch (ExpiredJwtException e) {
               System.out.println("Jwt Token has been expired");
           }
           catch (MalformedJwtException e) {
               System.out.println("Invalid Jwt token");
           }

       }
       else
       {
           System.out.println("Jwt token does not starts with Bearer");
       }

       // we have got the token and user name
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null)
        {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
           if(this.jwtTokenHelper.validateToken(token, userDetails))
           {

               UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                       userDetails,
                       null,
                       userDetails.getAuthorities());
               upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(upat);
           }
           else
           {
               System.out.println("Token has been expired or invalid username");
           }
        }
        else
        {
            System.out.println("Username is null or Security context is not null");
        }
        filterChain.doFilter(request,response);

    }
}
