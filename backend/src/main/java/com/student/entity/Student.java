package com.student.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("student")
public class Student {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String phone;
    private LocalDateTime createTime;
}
