package tn.vermeg.pfe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.vermeg.pfe.entites.Question;
import tn.vermeg.pfe.entites.User;
@Repository
public interface UserRepository extends MongoRepository<User,String>{
User findByUsername(String username);
    
    User findByEmail(String email);

	
}
