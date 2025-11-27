package com.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.student.entity.Teacher;

public interface TeacherService extends IService<Teacher> {
    // 先用通用的，如果以后有特殊业务，再在这里加自定义方法
}
