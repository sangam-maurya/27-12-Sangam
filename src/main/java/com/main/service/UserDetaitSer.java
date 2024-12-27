package com.main.service;

import com.main.entity.Student;
import com.main.reposietry.StudentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserDetaitSer implements UserDetailsService {
    private final StudentRepository studentRepository;

    public UserDetaitSer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username).orElseThrow(
                ()-> new RuntimeException("username is not present " + username));
        return new User(student.getUsername() , student.getPassword() ,new ArrayList<>());

    }
}
