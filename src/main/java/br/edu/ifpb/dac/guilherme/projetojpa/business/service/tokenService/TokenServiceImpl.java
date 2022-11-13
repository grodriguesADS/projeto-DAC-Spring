package br.edu.ifpb.dac.guilherme.projetojpa.business.service.tokenService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

	
    public static final String CLAIM_USERID = "userid";
    public static final String CLAIM_USERNAME = "username";
    public static final String CLAIM_EXPIRATION = "expirationTime";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;
    
    
	@Override
	public String generate(Owner owner) {
		 long expiration = Long.valueOf(this.expiration);
	        LocalDateTime expirationLocalDateTime = LocalDateTime.now().plusMinutes(expiration);
	        Instant expirationInstant = expirationLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
	        Date expirationDate = Date.from(expirationInstant);
	        String tokenExpiration = expirationLocalDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
	        String token = Jwts
	                        .builder()
	                        .setExpiration(expirationDate)
	                        .setSubject(owner.getId().toString())
	                        .claim(CLAIM_USERID, owner.getId())
	                        .claim(CLAIM_USERNAME, owner.getName())
	                        .claim(CLAIM_EXPIRATION, tokenExpiration)
	                        .signWith(SignatureAlgorithm.HS512, secret)
	                        .compact();
	        return token;
		
	}

	@Override
	public Claims getClaims(String token) throws ExpiredJwtException {
		 return Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody();
	}

	@Override
	public boolean isValid(String token) {
	     if(token == null)
	            return false;
	        try{
	            Claims claims = getClaims(token);
	            LocalDateTime expirationDate = claims.getExpiration().toInstant()
	            .atZone(ZoneId.systemDefault()).toLocalDateTime();

	            return!LocalDateTime.now().isAfter(expirationDate);
	        }catch(Exception e){
	            return false;
	        }
	}

	@Override
	public String getUsername(String token) {
		 Claims claims = getClaims(token);
	       return (String) claims.get(CLAIM_USERNAME);
	}

	@Override
	public long getUserId(String token) {
	    Claims claims = getClaims(token);
        return Long.parseLong(claims.getSubject());
	}

	@Override
	public String get(HttpServletRequest request) {
		 String authorization = request.getHeader("Authorization");
	        if(authorization == null|| !authorization.startsWith("Bearer")){
	            return null;
	        }
	        return authorization.split(" ")[1];
	}

}
