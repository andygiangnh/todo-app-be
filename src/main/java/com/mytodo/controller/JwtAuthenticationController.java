package com.mytodo.controller;

import com.mytodo.request.SignupRequest;
import com.mytodo.response.MessageResponse;
import com.mytodo.response.UserResponse;
import com.mytodo.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mytodo.service.JwtUserDetailsService;
import com.mytodo.jwt.config.JwtTokenUtil;
import com.mytodo.request.JwtRequest;
import com.mytodo.response.JwtResponse;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final UserResponse user = new UserResponse(userDetails.getUsername(),
                ((UserDetailsImpl)userDetails).getEmail(),
                userDetails.getAuthorities()
                        .stream()
                        .map(authority -> ((GrantedAuthority) authority).getAuthority())
                        .collect(Collectors.toList()));

        return ResponseEntity.ok(new JwtResponse(token, user));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody SignupRequest signupRequest) throws Exception {
        try {
            userDetailsService.save((signupRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing input");
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
