package br.edu.ifpb.dac.guilherme.projetojpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.model.dao.CarDAO;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Car;



@Service
public class CarService {
	
	@Autowired
	private CarDAO carDAO;
	
	public void save(Car car) {
		carDAO.save(car);
	}

	public void delete(Integer id) {
		carDAO.deleteById(id);
	}

	public void update(Car car) {
		carDAO.save(car);
	}
	
	public List<Car> find(Car car) {
		Example<Car> example = Example.of(car, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return carDAO.findAll(example);
	}

}
