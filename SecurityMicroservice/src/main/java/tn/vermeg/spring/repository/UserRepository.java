package tn.vermeg.spring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import tn.vermeg.spring.models.User;



public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

 // Optional<User> existsByEmail(String email);
  Boolean existsByEmail(String email);
  Optional<User> findByEmail(String email);
}
