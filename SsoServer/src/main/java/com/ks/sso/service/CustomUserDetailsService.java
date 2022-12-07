package com.ks.sso.service;

import java.util.logging.Logger;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ks.sso.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

//    @Autowired
//    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return new UserPrincipal(email);
    }


//    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id).orElse(null);
//
//        return UserPrincipal.create(user);
//    }
}
