package com.dev0kch.mybook.controller;


import com.dev0kch.mybook.model.AuthRequest;
import com.dev0kch.mybook.model.User;
import com.dev0kch.mybook.repository.UserRepository;
import com.dev0kch.mybook.service.CustomUserDetailsService;
import com.dev0kch.mybook.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;


    public AuthController(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest)throws Exception{
        try {


            User user = userRepository.findByUsername(authRequest.getUsername());
            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())){
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
                );
            }


        }catch (Exception e){

            throw new Exception("Invalid username or password");
        }

       return jwtUtil.generateToken(authRequest.getUsername());
    }

    @PutMapping("/authenticate/register")
    public void login(@RequestBody User user){
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

}
