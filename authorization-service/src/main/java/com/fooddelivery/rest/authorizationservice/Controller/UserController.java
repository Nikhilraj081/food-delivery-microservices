package com.fooddelivery.rest.authorizationservice.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.rest.authorizationservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.authorizationservice.Model.Address;
import com.fooddelivery.rest.authorizationservice.Model.User;
import com.fooddelivery.rest.authorizationservice.Repository.UserRepository;
import com.fooddelivery.rest.authorizationservice.Service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/userName/{userName}")
    public ResponseEntity<UserDetails> getUserByEmail(@PathVariable("userName") String userName)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return ResponseEntity.ok().body(userDetails);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") String id) {

        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/id/{id}/add/address")
    public ResponseEntity<?> addAddress(@Valid @RequestBody Address address, @PathVariable("id") String id) {
        
        User user = userService.setAddress(address, id);
        return ResponseEntity.created(null).body(user);
    }

    @GetMapping("/{userId}/address/{addressId}")
    public ResponseEntity<?> getAddressById(@PathVariable String userId, @PathVariable String addressId)
            throws ResourceNotFoundException {

        Address address = userService.getAddressById(userId, addressId);
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/{userId}/address/{addressId}/delete")
    public ResponseEntity<?> deleteAddress(@PathVariable("userId") String userId ,@PathVariable("addressId") String addressId)
    {
       User user = userService.deleteaddress(userId,addressId);
       return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody User user)
    {
        User newUser = userService.updateUser(userId,user);

        return ResponseEntity.created(null).body(newUser);
    }

}
