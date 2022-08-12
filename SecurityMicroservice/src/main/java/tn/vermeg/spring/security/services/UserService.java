package tn.vermeg.spring.security.services;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	boolean updatePassword(String email, String oldPass, String newPAss);
	boolean updateUsername(String email, String username);
	boolean updateImageName(String email, String imgName);
	String getUserImage(String email);
}
