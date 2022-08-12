package tn.vermeg.spring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import tn.vermeg.spring.models.ERole;
import tn.vermeg.spring.models.Role;



public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);

ERole save(ERole eRole);
}
