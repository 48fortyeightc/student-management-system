package com.student.service;

import com.student.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> list(String name, String gender);

    Student getById(Integer id);

    boolean save(Student student);

    boolean updateById(Student student);

    boolean removeById(Integer id);
}
