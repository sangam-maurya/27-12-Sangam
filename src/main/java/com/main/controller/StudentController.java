package com.main.controller;

import com.main.entity.Student;
import com.main.payload.LoginDto;
import com.main.payload.StudentDto;
import com.main.payload.TokenDto;
import com.main.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/v1/26")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<String>createStudent(@Valid @RequestBody Student student){
        StudentDto student1 = service.createStudent(student);
        if (student1!=null){
            return new ResponseEntity<>("Student detail insert" , HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("StudentDetail is null" , HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get")
    public List<StudentDto> getList(
            @RequestParam (value = "pageNo", defaultValue = "0" , required = false)int pageNo,
            @RequestParam (value ="pageSize", defaultValue = "5" , required = false)int pageSize,
            @RequestParam (value ="sortBy", defaultValue = "id" , required = false)String sortBy,
            @RequestParam (value ="sortDir", defaultValue = "asc" , required = false)String sortDir
            )
    {
        List<StudentDto> allStu = service.getAllStu(pageNo , pageSize , sortBy , sortDir);
        return allStu;
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto>getStudentById(@PathVariable long id){
        StudentDto byId = service.findById(id);
        return ResponseEntity.ok(byId);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDto>updateStudent(@PathVariable long id , @RequestBody Student student){
        StudentDto updated = service.updateStudentById(id, student);
        return ResponseEntity.ok(updated);
    }
    @PostMapping("/login")
    public ResponseEntity<?>veryFyLogin(@RequestBody LoginDto loginDto){
        String token = service.veryFyLogin(loginDto);
        if (token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setJwt("JWT");
        return new ResponseEntity<>(tokenDto , HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Invalid Password" , HttpStatus.BAD_REQUEST);
        }
    }
}
