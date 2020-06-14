package com.xebia.Springsecurityjpa.service;

import com.xebia.Springsecurityjpa.model.User;
import com.xebia.Springsecurityjpa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user =userRepo.findByUserName(userName);
        user.orElseThrow(()->new UsernameNotFoundException("Not Found "+userName));  //Lambda expression
       return user.map(com.xebia.Springsecurityjpa.model.UserDetails::new).get();

    }
}
