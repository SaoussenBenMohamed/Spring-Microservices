package tn.vermeg.spring;

import java.nio.charset.IllegalCharsetNameException;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import tn.vermeg.spring.models.ERole;
import tn.vermeg.spring.models.Role;
import tn.vermeg.spring.models.User;
import tn.vermeg.spring.repository.RoleRepository;
import tn.vermeg.spring.repository.UserRepository;


@EnableDiscoveryClient
@SpringBootApplication
public class SecurityMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityMicroserviceApplication.class, args);
	}
	 @Bean CommandLineRunner runner(RoleRepository roleRepository, UserRepository userRepository) { return args
			  -> {roleRepository.deleteAll();
			  
			  Role roleADMIN = new Role(); roleADMIN.setName(ERole.ROLE_ADMIN);
			  roleRepository.save(roleADMIN );
			 
			  
			  Role roleUser = new Role(); roleUser.setName(ERole.ROLE_USER);
			  roleRepository.save(roleUser );
			  User useradmin =new User(); 
			  useradmin.setUsername("saoussenadmin");
			  useradmin.setPassword("194JMT4427");
			  useradmin.setEmail("admin@gmail.com");
			  
			  Role roles = new Role();
			  roles.setName(ERole.ROLE_ADMIN);
			  //useradmin.setRoles(roles);
			  //useradmin.setRoles(setName(ERole.ROLE_ADMIN));
			  userRepository.save(useradmin);
			  
			  }; }
}
