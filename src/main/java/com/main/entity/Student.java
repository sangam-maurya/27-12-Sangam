package com.main.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name" , nullable = false)
    @NotBlank(message = "name can not be blank")
//    @Size(min = 3 , max = 5 , message = "name must be between 3 and 10 characters")
    private String name;
    @Column(name = "email" , nullable = false )
    @NotBlank(message = "email can not blank")
    private String email;
    @Column(name = "mobile" , nullable = false)
    @Size(min =10 , max = 10 , message = "mobile number should be 10 characters")
    private String mobile;
    @Column(name = "address" , nullable = false)
    @NotBlank(message = "Address can not be blank")
    private String address;
    @NotBlank(message = "username can not blank")
    @Size(min = 4 , max = 10 , message = "username must be between 4 and 10 characters")
    private String username;
    @NotBlank(message = "password can not  blank")
    @Size(min = 4 , message = "password must be at least 4 characters")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
