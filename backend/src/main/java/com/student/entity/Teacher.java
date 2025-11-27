package com.student.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("teacher")
public class Teacher {
    private Integer id;
    private String name;
    private String subject;
    private String phone;
    private LocalDateTime createTime;
}
