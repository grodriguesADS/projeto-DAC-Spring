package br.edu.ifpb.dac.guilherme.projetojpa.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Car;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;
import br.edu.ifpb.dac.guilherme.projetojpa.presentation.dto.CarDTO;
import br.edu.ifpb.dac.guilherme.projetojpa.presentation.dto.OwnerDTO;

@Service
public class ConverterService {
	
	public CarDTO carToDTO(Car car) {
		CarDTO dto = new CarDTO();
		dto.setId(car.getId());
		dto.setLicensePlate(car.getLicensePlate());
		
		return dto;
	}
	
	public Car dTOToCar (CarDTO dto) {
		Car car = new Car();
		car.setId(dto.getId());
		car.setLicensePlate(dto.getLicensePlate());
		
		return car;
	}
	
	public List<CarDTO> listCarToDTO(List<Car> carArray) {
		List<CarDTO> dtos = new ArrayList<>();
		
		for(Car car : carArray) {
			CarDTO dto = carToDTO(car);
			dtos.add(dto);
		}
		return dtos;
		}
	
	public List<Car> ListDTOToCar(List<CarDTO> dtos) {
		List<Car> carArray = new ArrayList<>();
		
		for(CarDTO dto : dtos) {
			Car car = dTOToCar(dto);
			carArray.add(car);
		}
		return carArray;
	}
	
//--------------------------------------------------------------------
	
	public OwnerDTO ownerToDTO(Owner owner) {
		OwnerDTO dto = new OwnerDTO();
		dto.setId(owner.getId());
		dto.setName(owner.getName());
		dto.setEmail(owner.getEmail());
		
		return dto;
	}
	
	public Owner dTOToOwner (OwnerDTO dto) {
		Owner owner = new Owner();
		owner.setName(dto.getName());
		owner.setEmail(dto.getEmail());
		owner.setPassword(dto.getPassword());
		
		return owner;
	}
	
	public List<OwnerDTO> listOwnerToDTO(List<Owner> ownerArray) {
		List<OwnerDTO> dtos = new ArrayList<>();
		
		for(Owner owner : ownerArray) {
			OwnerDTO dto = ownerToDTO(owner);
			dtos.add(dto);
		}
		return dtos;
		}
	
	public List<Owner> ListDTOToOwner(List<OwnerDTO> dtos) {
		List<Owner> ownerArray = new ArrayList<>();
		
		for(OwnerDTO dto : dtos) {
			Owner owner = dTOToOwner(dto);
			ownerArray.add(owner);
		}
		return ownerArray;
	}
	
	
	

}
