package com.main.service;

import com.main.entity.Student;
import com.main.payload.LoginDto;
import com.main.payload.StudentDto;
import com.main.reposietry.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;
    private final JwtService jwtService;

    public StudentService(StudentRepository studentRepository, ModelMapper mapper, JwtService jwtService) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.jwtService = jwtService;
    }

    public StudentDto createStudent(Student student){
        String hashpw = BCrypt.hashpw(student.getPassword(), BCrypt.gensalt(5));
        student.setPassword(hashpw);
        Student save = studentRepository.save(student);
        return  mapper.map(save , StudentDto.class);
    }

    public String veryFyLogin(LoginDto loginDto){
        Optional<Student> username = studentRepository.findByUsername(loginDto.getUsername());
        if (username.isPresent()){
            Student student = username.get();
            if (BCrypt.checkpw(loginDto.getPassword(), student.getPassword())){
                String token = jwtService.generateToken(loginDto.getUsername());
            return token;
            }else {
                return "password mismatch for user";
            }
        }else {
        return "user not found ";
        }
    }

    public List<StudentDto> getAllStu(int pageNo , int pageSize , String sortBy , String sortDir){
       Sort sort =  sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
               Sort.by(sortBy).descending();
        Pageable pageable =  PageRequest.of(pageNo , pageSize , sort);
        Page<Student> all = studentRepository.findAll(pageable);
        List<StudentDto> studentDtoList = all.stream().map(m -> mapper.map(m, StudentDto.class)).toList();
        return studentDtoList;
//        List<Student> students = studentRepository.findAll(pageable).getContent();
//        return students.stream()
//                .map(student -> mapper.map(student, StudentDto.class))
//                .toList();
    }

    public StudentDto findById(long id){
       Student student = studentRepository.findById(id).orElseThrow(()->
               new RuntimeException("id is not present " + id));
       return mapper.map(student , StudentDto.class);
    }

    public StudentDto updateStudentById(long id , Student student){
        Student student1 = studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("id is not present " + id));
        student1.setUsername(student.getUsername());
        student1.setPassword(student.getPassword());
        student1.setAddress(student.getAddress());
        student1.setEmail(student.getEmail());
        student1.setMobile(student.getMobile());
        student1.setName(student.getName());
        String hashpw = BCrypt.hashpw(student1.getPassword(), BCrypt.gensalt(5));
        student1.setPassword(hashpw);
        Student save = studentRepository.save(student1);
        return mapper.map(save ,StudentDto.class);
    }
}
