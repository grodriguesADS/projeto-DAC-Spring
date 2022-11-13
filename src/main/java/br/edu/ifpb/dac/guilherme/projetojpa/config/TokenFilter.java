package br.edu.ifpb.dac.guilherme.projetojpa.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces.SystemUserService;
import br.edu.ifpb.dac.guilherme.projetojpa.business.service.tokenService.TokenService;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;

public class TokenFilter extends OncePerRequestFilter {
	
	
	private TokenService tokenService;
	
    private SystemUserService systemUserService;

	public TokenFilter(TokenService tokenService, SystemUserService systemUserService) {
	      super();
	        this.tokenService = tokenService;
	        this.systemUserService = systemUserService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = tokenService.get(request);
        boolean valid = tokenService.isValid(token);
        if(valid){
            authenticate(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String token){
        Long userId = tokenService.getUserId(token);
        Owner owner = systemUserService.findById(userId);
        UsernamePasswordAuthenticationToken authentication = 
        new UsernamePasswordAuthenticationToken(owner, null, owner.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
		
}