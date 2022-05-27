package com.springsecurity.service;

import com.springsecurity.model.domain.User;
import com.springsecurity.model.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = repository.findByUsername(username);
        if(userEntity == null){
            throw new UsernameNotFoundException("Login ou senha incorretos! ");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        userEntity.getRoles().forEach( x -> { authorities.add( new SimpleGrantedAuthority( "ROLE_" + x ));
        });
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(), userEntity.getPassword(), authorities);
        return  userDetails;

    }
}
