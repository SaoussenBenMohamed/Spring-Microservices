package tn.vermeg.spring.controllers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import tn.vermeg.spring.models.ERole;
import tn.vermeg.spring.models.Role;
import tn.vermeg.spring.models.User;
import tn.vermeg.spring.payload.request.LoginRequest;
import tn.vermeg.spring.payload.request.SignupRequest;
import tn.vermeg.spring.payload.response.JwtResponse;
import tn.vermeg.spring.payload.response.MessageResponse;
import tn.vermeg.spring.repository.RoleRepository;
import tn.vermeg.spring.repository.UserRepository;
import tn.vermeg.spring.security.jwt.JwtUtils;
import tn.vermeg.spring.security.services.UserDetailsImpl;
import tn.vermeg.spring.security.services.UserDetailsServiceImpl;
import tn.vermeg.spring.security.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	
	@Autowired
	UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				
				
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	
	
	
	@PostMapping(path="/updatePassword")
	public ResponseEntity<Boolean> updatePassword( @RequestBody ObjectNode json
			){
		String email;
		String oldPass;
		String newPass;

		try {
			email = new ObjectMapper().treeToValue(json.get("email"), String.class);
			oldPass = new ObjectMapper().treeToValue(json.get("oldPass"), String.class);
			newPass = new ObjectMapper().treeToValue(json.get("newPass"), String.class);

			boolean test = this.userService.updatePassword(email, oldPass, newPass);
			if(test)
			return new ResponseEntity<Boolean>(test,HttpStatus.OK);

		} catch (JsonProcessingException e) {
			System.out.println("Parsing Exception!!");
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);

		}			
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);

			
		}
	
	
	
	
	@GetMapping(path="/getUserImageName")
	public Map<String,String> getUserImageName(@RequestParam(value="email", required=true) String email
			){
	    return Collections.singletonMap("imgn",this.userService.getUserImage(email));

		}
	
	
	
	@PostMapping(path="/updateUsername")
	public ResponseEntity<Boolean> updateUsername( @RequestBody ObjectNode json
			){
		String email;
		String username;
		try {
			email = new ObjectMapper().treeToValue(json.get("email"), String.class);
			username = new ObjectMapper().treeToValue(json.get("username"), String.class);
			boolean test = this.userService.updateUsername(email, username);
			if(test)
			return new ResponseEntity<Boolean>(test,HttpStatus.OK);

		} catch (JsonProcessingException e) {
			System.out.println("Parsing Exception!!");
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);

		}			
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);

			
		}
	
	@PostMapping(path="/updateImageName")
	public ResponseEntity<Boolean> updateImageName( @RequestBody ObjectNode json
			){
		String email;
		String imageName;
		try {
			email = new ObjectMapper().treeToValue(json.get("email"), String.class);
			imageName = new ObjectMapper().treeToValue(json.get("imageName"), String.class);
			boolean test = this.userService.updateImageName(email, imageName);
			if(test)
			return new ResponseEntity<Boolean>(test,HttpStatus.OK);

		} catch (JsonProcessingException e) {
			System.out.println("Parsing Exception!!");
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);

		}			
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_ACCEPTABLE);

			
		}
	
	
	
	
	
	
	
	
	
	
}
