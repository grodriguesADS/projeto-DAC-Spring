package br.edu.ifpb.dac.guilherme.projetojpa.business.service.tokenService;

import javax.servlet.http.HttpServletRequest;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface TokenService {
	
	String generate(Owner owner);

    Claims getClaims(String token) throws ExpiredJwtException;

    boolean isValid(String token);

    String getUsername(String token);

    long getUserId(String token);

    String get(HttpServletRequest request);
		
}
