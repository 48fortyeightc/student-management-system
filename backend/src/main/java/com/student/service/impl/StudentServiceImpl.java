package com.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.student.entity.Student;
import com.student.mapper.StudentMapper;
import com.student.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> list(String name, String gender) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like(Student::getName, name);
        }
        if (StringUtils.hasText(gender)) {
            wrapper.eq(Student::getGender, gender);
        }
        wrapper.orderByDesc(Student::getCreateTime);
        return studentMapper.selectList(wrapper);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.selectById(id);
    }

    @Override
    public boolean save(Student student) {
        if (student.getCreateTime() == null) {
            student.setCreateTime(LocalDateTime.now());
        }
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
