package tn.vermeg.spring.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.vermeg.spring.models.ERole;
import tn.vermeg.spring.repository.RoleRepository;



@Service
public class RoleServiceImpl {
	@Autowired
	RoleRepository roleRepository;
	
	
	public ERole save (ERole eRole) {
		
		
		return roleRepository.save(eRole);
	}

}
