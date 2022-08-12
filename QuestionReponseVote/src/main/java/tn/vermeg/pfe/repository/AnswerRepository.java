package tn.vermeg.pfe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tn.vermeg.pfe.entites.Answer;


@Repository
public interface AnswerRepository extends  MongoRepository<Answer,String> {

}
