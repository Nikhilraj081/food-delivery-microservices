package com.fooddelivery.rest.authorizationservice.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.rest.authorizationservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.authorizationservice.Model.Address;
import com.fooddelivery.rest.authorizationservice.Model.User;
import com.fooddelivery.rest.authorizationservice.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") String id)
    {
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/id/{id}/add/address")
    public ResponseEntity<?> addAddress(@Valid @RequestBody Address address,@PathVariable("id") String id)
    {
       User user = userService.setAddress(address, id);

       return ResponseEntity.created(null).body(user);
    }

    @GetMapping("/{userId}/address/{addressId}")
    public ResponseEntity<?> getAddressById(@PathVariable String userId, @PathVariable String addressId) throws ResourceNotFoundException
    {
        Address address = userService.getAddressById(userId, addressId);

        return ResponseEntity.ok().body(address);
    }


}
