package com.example.springdemo.SecurityCofiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class jwtUtilityClass implements Serializable {

       // private static final long serialVersionUID = 7008375124389347049L;
       public static final long TOKEN_VALIDITY = 60 * 60;
        private final String jwtSecret = "SECRET";
        //function to generate jwt token
        public String generateJwtToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles",userDetails.getAuthorities());
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        }
        //function to validate jwt token
        public Boolean validateJwtToken(String token, UserDetails userDetails) {
            String username = getUsernameFromToken(token);
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            Boolean isTokenExpired = claims.getExpiration().before(new Date());
            return (username.equals(userDetails.getUsername()) && !isTokenExpired);
        }
        public String getUsernameFromToken(String token) {
            final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return claims.getSubject();
        }

}