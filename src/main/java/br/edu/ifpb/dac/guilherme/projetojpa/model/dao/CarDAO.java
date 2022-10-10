package br.edu.ifpb.dac.guilherme.projetojpa.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Car;

@Repository
public interface CarDAO extends JpaRepository<Car, Integer>  {

}
