package com.dev0kch.mybook.controller;


import com.dev0kch.mybook.model.AuthRequest;
import com.dev0kch.mybook.model.User;
import com.dev0kch.mybook.repository.UserRepository;
import com.dev0kch.mybook.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity generateToken(@RequestBody AuthRequest authRequest)throws Exception{
        ResponseEntity token;
        try {


            User user = userRepository.findByUsername(authRequest.getUsername());
            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword()) &&
                    user.getUsername().equals(authRequest.getUsername())){

                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), user.getPassword())
                );
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("authorization", jwtUtil.generateToken(authRequest.getUsername()));

//                token = ResponseEntity.ok().header(responseHeaders.toString()).body("Success");
                token = new ResponseEntity(responseHeaders, HttpStatus.OK);
            }else {
                throw new Exception("Invalid username or password");

            }



        }catch (Exception e){

            throw new Exception("Invalid username or password");
        }

        return token;
    }

    @PostMapping("/authenticate/register")
    public User login(@RequestBody User user) throws Exception {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        if (userRepository.findByUsername(user.getUsername()) == null){
            userRepository.save(user);
        }
        else {
            throw new Exception("username is already exist");
        }
        return user;
    }


    // if response is true, the token is expired
    // if response is false, the token is valid
    @PostMapping("/authenticate/validate_token")
    public ResponseEntity validateToken(@RequestBody  String token){
        boolean isValidateToken= true;
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity response;
        try {
            isValidateToken =  jwtUtil.isTokenExpired(token);

        }catch (Exception e){
            isValidateToken = true;
        }
        finally {
            responseHeaders.add("check_token", String.valueOf(isValidateToken));
            response = new ResponseEntity(responseHeaders, HttpStatus.OK);
        }

        return response;
    }

    @PostMapping("users/delete")
    public void delete(@RequestBody Long id){

        userRepository.deleteById(id);


    }






}
