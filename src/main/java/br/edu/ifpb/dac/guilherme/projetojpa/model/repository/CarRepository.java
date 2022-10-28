package br.edu.ifpb.dac.guilherme.projetojpa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>  {

}
