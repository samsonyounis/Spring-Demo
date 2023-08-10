package com.example.springdemo.Controller;


import com.example.springdemo.Entities.*;
import com.example.springdemo.SecurityCofiguration.jwtUtilityClass;
import com.example.springdemo.SecurityCofiguration.userDetailsService;
import com.example.springdemo.Services.userAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Component
public class UserAccountController {
    @Autowired
    private userAccountService UserAccountService;
    @Autowired
    private userDetailsService myUserDetailsService;
    @Autowired
    private jwtUtilityClass jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;

    //login and authentication end point
    @PostMapping("/login")
    @Operation(method = "Post",summary = "this method authenticates the user",description = "" +
            "the user should provide the username and password")
    public ResponseEntity<?> authenticateAndCreateToken(@RequestBody LoginRequest request)
            throws Exception
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (request.getUsername(), request.getPassword()));
        //loading the user  details by username
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
        //generating token using the userDetails and assigning it to jwtToken variable
        final String jwtToken = jwtUtility.generateJwtToken(userDetails);
        //returning the token in the json body
        return ResponseEntity.ok(new LoginResponse(jwtToken));

    }

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello samson, how is your day!!";
    }

    //endpoint function to create account
    @PostMapping("/addUser")
    public UserAccount addUser(@RequestBody UserAccount account){

        return UserAccountService.addUser(account);
    }

    @PostMapping("/addRole")
    public Roles addRole(@RequestBody Roles role){

        return UserAccountService.addRole(role);
    }
    @GetMapping("/getUserRoles/{username}")
    public List<Roles> getUserRoles(@PathVariable("username") String username){
        return UserAccountService.getUserByname(username);
    }

    //endpoint function to get all accounts
    @GetMapping("/getAll")
    public Mono<List<UserAccount>> getAllUsers(){

        return Mono.fromCallable(()->UserAccountService.getAllUsers());
    }


    //endpoint function to get account by id
    @GetMapping("/getById/{id}")
    public Optional<account> getAccountById(@PathVariable("id") Integer id){

        return UserAccountService.getAccountById(id);
    }

    //endpoint function to delete an account by Id
    @DeleteMapping("/admin/delete/{id}")
    public void deleteAccount(@PathVariable("id") int id){
        UserAccountService.deleteAccount(id);
    }

    //endpoint function to update the username of account
    @PutMapping("/admin/update/{id}")
    public account updateAccount(@PathVariable("id") Integer id, @RequestBody account newAccount){
        return UserAccountService.updateAccount(id, newAccount);
    }

    // function to get all the accounts with paging and sorting
    @GetMapping("getAllSorted/{date}")
    List<account> findBydate(@PathVariable("date") String date, PageRequest pageRequest){
        return UserAccountService.findBydate
                (date, PageRequest.of(0,2, Sort.Direction.ASC,"userID"));
    }
}
