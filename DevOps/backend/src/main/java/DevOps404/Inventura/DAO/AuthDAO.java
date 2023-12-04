package DevOps404.Inventura.DAO;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DevOps404.Inventura.entity.User;
import DevOps404.Inventura.security.JwtUtils;
import DevOps404.Inventura.security.entity.JwtResponse;
import DevOps404.Inventura.security.entity.LoginRequest;

@Service
public class AuthDAO {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils utils;
	
	public ResponseEntity<JwtResponse> login(LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = utils.generateJwtToken(authentication);
				
		User userDetails = (User) authentication.getPrincipal();		
		String role = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList()).get(0);

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getUserId(), 
												 userDetails.getUsername(), 
												 role));
	}
}
