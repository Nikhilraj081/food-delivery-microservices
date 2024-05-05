package com.fooddelivery.rest.authorizationservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fooddelivery.rest.authorizationservice.Model.User;
import com.fooddelivery.rest.authorizationservice.Repository.UserRepository;

@Service("userDetailsService")
public class  CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmailId(username);

        if(user != null)
        {
            System.out.println("under load method");
            return user;
        }
        throw new UsernameNotFoundException(" user not found with emailId: "+username);
    }   

}
