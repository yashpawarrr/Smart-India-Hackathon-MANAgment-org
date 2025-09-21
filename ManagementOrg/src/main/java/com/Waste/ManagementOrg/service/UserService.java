package com.Waste.ManagementOrg.service;


import com.Waste.ManagementOrg.entity.User;
import com.Waste.ManagementOrg.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public User create(User user) {

        User u = new User();
        u.setId(user.getId());
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        u.setWalletBalance(user.getWalletBalance());
        return userRepository.save(u);
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//        return new org.springframework.security.core.userdetails.User(
//                user.getName(),
//                user.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
////                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
//        );

//    public User create(User user, String password) {
//    }


    public User get(Long id) {
        User byId = userRepository.getReferenceById(id);
        return byId;
    }


    public void delete(Long id) {
        User ex = userRepository.getById(id);
        userRepository.delete(ex);
    }
}

