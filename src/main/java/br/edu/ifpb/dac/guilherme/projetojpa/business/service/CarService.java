package br.edu.ifpb.dac.guilherme.projetojpa.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;


import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Car;
import br.edu.ifpb.dac.guilherme.projetojpa.model.repository.CarRepository;



@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public void save(Car car) {
		carRepository.save(car);
	}

	public void delete(Integer id) {
		carRepository.deleteById(id);
	}

	public void update(Car car) {
		carRepository.save(car);
	}
	
	public List<Car> find(Car car) {
		Example<Car> example = Example.of(car, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return carRepository.findAll(example);
	}

}
