package tn.vermeg.pfe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.vermeg.pfe.entites.Question;

import tn.vermeg.pfe.repository.QuestionRepository;
import tn.vermeg.pfe.repository.UserRepository;
@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	UserRepository userRepository;
	
	
	
	@Override
	public Question saveQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionRepository.save(question);
	}



	/*
	 * @Override public Question getQuestion(String id) { // TODO Auto-generated
	 * method stub Optional<Question> question = questionRepository.findById(id);
	 * if(question.isPresent()) { return question.get();}
	 * 
	 * }
	 */
	
	
}
