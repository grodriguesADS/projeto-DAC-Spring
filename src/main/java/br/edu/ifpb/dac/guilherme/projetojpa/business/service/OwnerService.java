package br.edu.ifpb.dac.guilherme.projetojpa.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces.SystemUserService;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.SystemRole;
import br.edu.ifpb.dac.guilherme.projetojpa.model.repository.OwnerRepository;

@Service
public class OwnerService implements SystemUserService {
	
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private SystemRoleServiceImpl systemRoleServiceImpl;
	
	@Autowired
	private PasswordEncorderServiceImpl passwordEncorderServiceImpl;
	
	@Override
	public Owner save(Owner owner) {
		if(owner.getId() != null){
			throw new IllegalStateException("Onwer exist");
		}
		passwordEncorderServiceImpl.encryptPassword(owner);
		
		List<SystemRole> roles = new ArrayList<>();
		roles.add(systemRoleServiceImpl.findDefault());
        owner.setRoles(roles);;
		return ownerRepository.save(owner);
	}
	
	
	public void delete (Long id) {
		ownerRepository.deleteById(id);
	}
	
	public Owner update (Owner owner) {
		if(owner.getId() == null){
			throw new IllegalStateException("Id is empty");
		}
		passwordEncorderServiceImpl.encryptPassword(owner);
		return ownerRepository.save(owner);
	}
	
	public List<Owner> find(Owner filter) {
		Example<Owner> example = Example.of(filter, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return ownerRepository.findAll(example);
	}
	
	public List<Owner> findAll () {
		return (List<Owner>) ownerRepository.findAll();
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Owner owner = findByEmail(username);
		if(owner == null){
			throw new UsernameNotFoundException(String.format("Could not find any use with usename %s", username));
		}
		
		return owner;
	}


	@Override
	public Owner findById(Long id) {
		Owner entity = ownerRepository.findById(id).get();
		return entity;
	}


	@Override
	public Owner findByEmail(String email) {
		List<Owner> user = ownerRepository.findByEmail(email);
		return (Owner) user.get(0);
	}


	@Override
	public Owner findByUseName(String name) {
		List<Owner> user = ownerRepository.findByName(name);
		return (Owner) user.get(0);
	}


	

}
