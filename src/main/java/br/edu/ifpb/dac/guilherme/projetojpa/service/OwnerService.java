package br.edu.ifpb.dac.guilherme.projetojpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.model.dao.OwnerDAO;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Car;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;

@Service
public class OwnerService {
	
	
	@Autowired
	private OwnerDAO ownerDAO;
	
	
	public void save(Owner owner) {
		ownerDAO.save(owner);
	}
	
	
	public void delete (Integer id) {
		ownerDAO.deleteById(id);
	}
	
	public void update (Owner owner) {
		ownerDAO.save(owner);
	}
	
	public List<Owner> find(Owner owner) {
		Example<Owner> example = Example.of(owner, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return ownerDAO.findAll(example);
	}
	
	public List<Owner> list () {
		return (List<Owner>) ownerDAO.findAll();
	}

}
