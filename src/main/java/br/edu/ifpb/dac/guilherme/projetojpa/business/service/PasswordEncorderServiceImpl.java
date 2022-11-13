package br.edu.ifpb.dac.guilherme.projetojpa.business.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces.PasswordEncoderService;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;

@Service
public class PasswordEncorderServiceImpl extends BCryptPasswordEncoder  implements PasswordEncoderService{

	@Override
	public void encryptPassword(Owner owner) {
		if(owner.getPassword() != null) {
			String encryptedPassword = encode(owner.getPassword());
			owner.setPassword(encryptedPassword);
		}
	}

}
