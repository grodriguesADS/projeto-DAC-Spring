package br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;

public interface SystemUserService extends UserDetailsService {
	
		public Owner save(Owner owner);
	    public Owner  update(Owner owner );

	    public void delete(Long id);

	    public Owner findById(Long userId);

	    public Owner findByEmail(String email);

	    public Owner findByUseName(String name);

	    public List<Owner > findAll();

	    public List<Owner> find(Owner  filter);
}
