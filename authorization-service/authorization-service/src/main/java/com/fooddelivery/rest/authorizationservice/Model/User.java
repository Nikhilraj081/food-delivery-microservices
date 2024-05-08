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

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "First Name should not be null")
    private String firstName;

    @NotEmpty(message = "Last Name should not be null")
    private String lastName;

    @NotEmpty(message = "Mobile no should not be null")
    @Size(min = 10, max = 10, message = "mobile number must be 10 digit")
    private String mobileNo;

    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email id should not be empty")
    private String emailId;

    @NotEmpty(message = "Password should not be null")
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
