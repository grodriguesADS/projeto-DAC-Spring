package br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;

public interface AuthenticationService {

	public String login(String email, String password);
	
	public Owner getLoggedUser();
}
