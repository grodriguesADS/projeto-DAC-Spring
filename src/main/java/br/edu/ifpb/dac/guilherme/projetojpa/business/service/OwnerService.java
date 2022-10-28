package br.edu.ifpb.dac.guilherme.projetojpa.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;
import br.edu.ifpb.dac.guilherme.projetojpa.model.repository.OwnerRepository;

@Service
public class OwnerService {
	
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	
	public void save(Owner owner) {
		ownerRepository.save(owner);
	}
	
	
	public void delete (Integer id) {
		ownerRepository.deleteById(id);
	}
	
	public void update (Owner owner) {
		ownerRepository.save(owner);
	}
	
	public List<Owner> find(Owner owner) {
		Example<Owner> example = Example.of(owner, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return ownerRepository.findAll(example);
	}
	
	public List<Owner> list () {
		return (List<Owner>) ownerRepository.findAll();
	}

}
