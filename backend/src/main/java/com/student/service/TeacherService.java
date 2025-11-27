package com.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.student.entity.Teacher;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    List<Teacher> list(String name, String subject);
}
