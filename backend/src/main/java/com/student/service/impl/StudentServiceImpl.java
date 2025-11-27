package com.student.service.impl;

import com.student.entity.Student;
import com.student.mapper.StudentMapper;
import com.student.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> list() {
        return studentMapper.selectList(null);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.selectById(id);
    }

    @Override
    public boolean save(Student student) {
        return studentMapper.insert(student) > 0;
    }

    @Override
    public boolean updateById(Student student) {
        return studentMapper.updateById(student) > 0;
    }

    @Override
    public boolean removeById(Integer id) {
        return studentMapper.deleteById(id) > 0;
    }
}
