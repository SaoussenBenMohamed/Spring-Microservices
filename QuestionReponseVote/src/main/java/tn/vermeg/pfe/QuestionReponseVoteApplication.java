package tn.vermeg.pfe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;



import tn.vermeg.pfe.entites.User;
import tn.vermeg.pfe.repository.UserRepository;
@EnableDiscoveryClient
@SpringBootApplication
public class QuestionReponseVoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionReponseVoteApplication.class, args);
	}
	@Bean CommandLineRunner runner(UserRepository userRepository) { return args
			  -> {userRepository.deleteAll();
			  
			 User user = new User(); user.setUsername("saoussen");
			 userRepository.save(user);
			  
			  
			  
			  
			  }; }
}
