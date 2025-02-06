package com.main.service;

import com.main.entity.Student;
import com.main.reposietry.StudentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public UserDetailService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the student by username
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));

        // Return UserDetails object
        return new User(
                student.getUsername(),
                student.getPassword(),
                Collections.singletonList(() -> "ROLE_" + student.getRole())
        );
    }
}
