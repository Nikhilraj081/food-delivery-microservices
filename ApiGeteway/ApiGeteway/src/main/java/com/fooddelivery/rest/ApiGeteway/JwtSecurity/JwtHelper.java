package com.fooddelivery.rest.ApiGeteway.JwtSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
import com.fooddelivery.rest.ApiGeteway.Configuration.Constants;

@Component
public class JwtHelper {

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
       
        return Jwts.parser().setSigningKey(Constants.JWT_SECRET).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {

        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    
    //validate token
    public Boolean validateToken(String token) {

       return (!isTokenExpired(token));
    }

}
