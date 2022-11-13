package br.edu.ifpb.dac.guilherme.projetojpa.business.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces.SystemRoleService;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.SystemRole;
import br.edu.ifpb.dac.guilherme.projetojpa.model.repository.SystemRoleRepository;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {

	@Autowired
	private SystemRoleRepository repository;
	

	public void createDefaultValues() {
		for (AVAILABLE_ROLES availableRole : AVAILABLE_ROLES.values()) {
		            SystemRole role = findByName(availableRole.name());
		            if (role == null) {
		                role = new SystemRole();
		                role.setName(availableRole.name());
		                repository.save(role);
		            }
		      }
	}


	@Override
	public SystemRole findByName(String name) {
		 if (name == null) {
	            throw new IllegalStateException("Name Cannot be null");
	        }
	        Optional<SystemRole> optional = repository.findByName(name);
	        return optional.isPresent()? optional.get():null;
	}


	@Override
	public SystemRole findDefault() {
		 return findByName(AVAILABLE_ROLES.USER.name());
	}
}
