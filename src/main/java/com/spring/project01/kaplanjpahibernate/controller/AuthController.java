package com.spring.project01.kaplanjpahibernate.controller;


import com.spring.project01.kaplanjpahibernate.data.entity.Role;
import com.spring.project01.kaplanjpahibernate.data.entity.User;
import com.spring.project01.kaplanjpahibernate.data.repository.IBookRepository;
import com.spring.project01.kaplanjpahibernate.data.repository.ICustomerRepository;
import com.spring.project01.kaplanjpahibernate.data.repository.IRoleRepository;
import com.spring.project01.kaplanjpahibernate.data.repository.IUserRepository;
import com.spring.project01.kaplanjpahibernate.dto.JWTAuthResponse;
import com.spring.project01.kaplanjpahibernate.dto.LoginDTO;
import com.spring.project01.kaplanjpahibernate.dto.SignUpDTO;
import com.spring.project01.kaplanjpahibernate.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    //step4

    @Autowired
    private AuthenticationManager authenticationManager;


//    @PostMapping("/signin")
//    public ResponseEntity<String> authenticatedUser(@RequestBody LoginDTO loginDTO) {
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEMail(),
//                        loginDTO.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
//    }

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {
//        //add check for username exists in db
//        if (iUserRepository.existsByUserName(signUpDTO.getUserName())) {
//            return new ResponseEntity<>("username is already taken ", HttpStatus.BAD_REQUEST);
//        }
//        // add check for e mail in db
//        if (iUserRepository.existsByEMail(signUpDTO.getEMail())) {
//            return new ResponseEntity<>("eMail is already taken ", HttpStatus.BAD_REQUEST);
//        }
//        //create user
//
//        User user = new User();
//        user.setUserName(signUpDTO.getUserName());
//        user.setEMail(signUpDTO.getEMail());
//        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
//
//        Optional<Role> roles = iRoleRepository.findByName("ROLE_ADMIN");
//        if (roles.isPresent()) {
//            user.setRoles(Collections.singleton(roles.get()));
//            iUserRepository.save(user);
//            return new ResponseEntity<>("user successfully registered!", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("there is a problem", HttpStatus.BAD_REQUEST);
//
//
//    }


    //step5

    @Autowired
    private JwtTokenProvider jwtTokenProvider;





    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> autehnticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEMail(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get token from provider
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {
        //add check for username exists in db
        if (iUserRepository.existsByUserName(signUpDTO.getUserName())) {
            return new ResponseEntity<>("username is already taken ", HttpStatus.BAD_REQUEST);
        }
        // add check for e mail in db
        if (iUserRepository.existsByEMail(signUpDTO.getEMail())) {
            return new ResponseEntity<>("eMail is already taken ", HttpStatus.BAD_REQUEST);
        }
        //create user

        User user = new User();
        user.setUserName(signUpDTO.getUserName());
        user.setEMail(signUpDTO.getEMail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        Optional<Role> roles = iRoleRepository.findByName("ROLE_ADMIN");
        if (roles.isPresent()) {
            user.setRoles(Collections.singleton(roles.get()));
            iUserRepository.save(user);
            return new ResponseEntity<>("user successfully registered!", HttpStatus.OK);
        }
        return new ResponseEntity<>("there is a problem", HttpStatus.BAD_REQUEST);


    }


}
