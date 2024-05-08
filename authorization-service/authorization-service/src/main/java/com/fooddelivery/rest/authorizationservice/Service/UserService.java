package com.fooddelivery.rest.authorizationservice.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fooddelivery.rest.authorizationservice.Exception.ApiException;
import com.fooddelivery.rest.authorizationservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.authorizationservice.Model.Address;
import com.fooddelivery.rest.authorizationservice.Model.User;
import com.fooddelivery.rest.authorizationservice.Repository.UserRepository;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User setUser(User user)
    {
        if(userRepository.findByEmailId(user.getEmailId()) != null)
        {
            throw new ApiException("One user is already registered with email id: "+user.getEmailId());
        }

        if(userRepository.findByMobileNo(user.getMobileNo()) != null)
        {
            throw new ApiException("One user is already registered with mobile no: "+user.getMobileNo());
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String id) {

       Optional<User> user =userRepository.findById(id);
       return  user;
    }

    //set user address
    public User setAddress(Address address, String userId)
    {
        address.setId(UUID.randomUUID().toString());

        User user = getUserById(userId).get();

        List<Address> newAddress = user.getAddress();
        newAddress.add(address);

        user.setAddress(newAddress);
        
        return userRepository.save(user);
    }

    public Address getAddressById(String userId, String addressId) throws ResourceNotFoundException
    {
        User user = getUserById(userId).get();

        List<Address> address = user.getAddress();

        for(Address val : address)
        {
            if(val.getId().equals(addressId))
            {
                return val;
            }
        }

        throw new ResourceNotFoundException("Address not found with id: "+addressId);
    }

}
