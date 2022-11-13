package br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;

public interface PasswordEncoderService extends PasswordEncoder{
	
	void encryptPassword (Owner owner);

}
