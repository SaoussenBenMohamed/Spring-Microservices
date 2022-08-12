package tn.vermeg.pfe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.vermeg.pfe.entites.Question;
@Repository
public interface QuestionRepository extends  MongoRepository<Question,String>{

}
