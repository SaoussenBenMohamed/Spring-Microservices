package tn.vermeg.spring.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import tn.vermeg.spring.models.User;
import tn.vermeg.spring.repository.UserRepository;
@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	UserRepository userRepository;
	 @Autowired
	  private PasswordEncoder passwordEncoder;
	@Override
	public boolean updatePassword(String email, String oldPass, String newPAss) {
		Optional<User> opt = this.userRepository.findByEmail(email);
		User  user;
		if(opt.isPresent()) {
			user =  opt.get();
			if( passwordEncoder.matches(oldPass, user.getPassword())) {
				user.setPassword( passwordEncoder.encode(newPAss));
				 this.userRepository.save(user);
				 return true;
			}
		
		}
		return false;
	}
	@Override
	public boolean updateUsername(String email, String username) {
		Optional<User> opt = this.userRepository.findByEmail(email);
		User user;
		if(opt.isPresent()) {
			user =  opt.get();
			user.setUsername(username);
			 this.userRepository.save(user);
			 return true;
		}
		return false;
	}
	@Override
	public boolean updateImageName(String email, String imgName) {
		Optional<User> opt = this.userRepository.findByEmail(email);
		User user;
		if(opt.isPresent()) {
			user =  opt.get();
			user.setImageName(imgName);
			 this.userRepository.save(user);
			 return true;
		}
		return false;
	}
	@Override
	public String getUserImage(String email) {
		Optional<User> opt = this.userRepository.findByEmail(email);
		User user;
		if(opt.isPresent()) {
			user =  opt.get();

			 return user.getImageName() ;
		}
		return "" ;
	}


}
