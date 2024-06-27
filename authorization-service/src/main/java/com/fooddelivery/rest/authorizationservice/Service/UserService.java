package com.fooddelivery.rest.authorizationservice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fooddelivery.rest.authorizationservice.Exception.ApiException;
import com.fooddelivery.rest.authorizationservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.authorizationservice.Model.Address;
import com.fooddelivery.rest.authorizationservice.Model.User;
import com.fooddelivery.rest.authorizationservice.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RestClient restClient;

    public User setUser(User user) {
        if (userRepository.findByEmailId(user.getEmailId()) != null) {
            throw new ApiException("One user is already registered with email id: " + user.getEmailId());
        }

        if (userRepository.findByMobileNo(user.getMobileNo()) != null) {
            throw new ApiException("One user is already registered with mobile no: " + user.getMobileNo());
        }

        List<Address> addressList = new ArrayList<>();

        user.setAddress(addressList);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User newUser = userRepository.save(user);

        // if (newUser != null) {

        // restClient.post().uri("/cart/create/{userId}", newUser.getId()).retrieve();
        // }

        return newUser;
    }

    public Optional<User> getUserById(String id) {

        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public User updateUser(String userId, User user) {
        User newUser = getUserById(userId).get();

        if (newUser.getEmailId().equals(user.getEmailId())) {
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmailId(user.getEmailId());
            newUser.setMobileNo(user.getMobileNo());

            return userRepository.save(newUser);

        } else {

            if (userRepository.findByEmailId(user.getEmailId()) == null) {
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setEmailId(user.getEmailId());
                newUser.setMobileNo(user.getMobileNo());

                return userRepository.save(newUser);
            }
        }
        throw new ApiException("One user is alrady registed with this email id, please try another");

    }

    // set user address
    public User setAddress(Address address, String userId) {
        address.setId(UUID.randomUUID().toString());

        User user = getUserById(userId).get();

        List<Address> newAddress = user.getAddress();
        newAddress.add(address);

        user.setAddress(newAddress);

        return userRepository.save(user);
    }

    public Address getAddressById(String userId, String addressId) throws ResourceNotFoundException {
        User user = getUserById(userId).get();

        List<Address> address = user.getAddress();

        for (Address val : address) {
            if (val.getId().equals(addressId)) {
                return val;
            }
        }

        throw new ResourceNotFoundException("Address not found with id: " + addressId);
    }

    public User deleteaddress(String userId, String addressId) {

        User user = getUserById(userId).get();

        List<Address> address = new ArrayList<>();

        for (Address val : user.getAddress()) {

            if (!val.getId().equals(addressId)) {
                address.add(val);
            }
        }
        user.setAddress(address);
        return userRepository.save(user);
    }

}
