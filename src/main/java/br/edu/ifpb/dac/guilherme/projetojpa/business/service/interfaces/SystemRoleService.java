package br.edu.ifpb.dac.guilherme.projetojpa.business.service.interfaces;

import br.edu.ifpb.dac.guilherme.projetojpa.model.entity.SystemRole;

public interface SystemRoleService {
           
	public enum AVAILABLE_ROLES { ADMIN ,USER } 
	
	 public SystemRole findByName(String name);
	 public SystemRole findDefault();
}
