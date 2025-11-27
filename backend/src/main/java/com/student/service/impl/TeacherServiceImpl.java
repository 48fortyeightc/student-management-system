package com.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.entity.Teacher;
import com.student.mapper.TeacherMapper;
import com.student.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl
        extends ServiceImpl<TeacherMapper, Teacher>
        implements TeacherService {

    // 目前通用 CRUD 就够用了，所以这里可以什么都不写
}
