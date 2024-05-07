package com.fooddelivery.rest.authorizationservice.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Component
public class User implements UserDetails  {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private String password;
    private List<Address> address; 


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }
    @Override
    public String getUsername() {
       
        return this.emailId;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        
        return true;
    }

}
