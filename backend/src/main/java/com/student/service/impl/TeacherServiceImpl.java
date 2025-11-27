package com.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.entity.Teacher;
import com.student.mapper.TeacherMapper;
import com.student.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TeacherServiceImpl
        extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherService {

    @Override
    public List<Teacher> list(String name, String subject) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like(Teacher::getName, name);
        }
        if (StringUtils.hasText(subject)) {
            wrapper.like(Teacher::getSubject, subject);
        }
        wrapper.orderByDesc(Teacher::getCreateTime);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public boolean save(Teacher entity) {
        if (entity.getCreateTime() == null) {
            entity.setCreateTime(java.time.LocalDateTime.now());
        }
        return super.save(entity);
    }
}
