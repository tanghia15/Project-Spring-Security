package com.example.exam.sevice;

import com.example.exam.entity.Role;
import com.example.exam.entity.User;
import com.example.exam.entity.UserPrinciple;
import com.example.exam.repository.RoleRepo;
import com.example.exam.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private RoleRepo roleRepo;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        List<Role> roles = roleRepo.findRolesByUserId(user.getId());
        return new UserPrinciple(user,roles);
    }
}
