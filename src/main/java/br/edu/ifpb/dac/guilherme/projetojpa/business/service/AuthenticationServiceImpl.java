package br.edu.ifpb.dac.guilherme.projetojpa.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces.AuthenticationService;
import br.edu.ifpb.dac.guilherme.projetojpa.business.service.tokenService.TokenService;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

		@Autowired
	    private OwnerService ownerService;
	    @Autowired
	    private TokenService tokenService;
	    @Autowired
	    private AuthenticationManager authManager;

	    @Override
	    public String login(String username, String password) {
	        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	        Owner owner = ownerService.findByEmail(username);
	        return tokenService.generate(owner);
	    }

	    @Override
	    public Owner getLoggedUser() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        return (Owner) auth.getPrincipal();
	    }
}
