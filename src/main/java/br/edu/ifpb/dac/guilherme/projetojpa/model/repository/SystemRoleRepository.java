package br.edu.ifpb.dac.guilherme.projetojpa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.Owner;
import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.SystemRole;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRole, Integer>{

	Optional<SystemRole> findByName(String name);
}
