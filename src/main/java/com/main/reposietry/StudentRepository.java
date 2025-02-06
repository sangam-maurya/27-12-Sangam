package com.main.reposietry;

import com.main.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);
    @Query("SELECT s FROM Student s WHERE s.name = :name")
    Optional<Student> findByName(@Param("name") String name);
}