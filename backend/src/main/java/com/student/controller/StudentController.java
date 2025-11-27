package com.student.controller;

import com.student.entity.Student;
import com.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Resource
    private StudentService studentService;

    // 1. 查询所有学生：GET /api/students
    @GetMapping
    public List<Student> list() {
        return studentService.list();
    }

    // 2. 按 id 查询：GET /api/students/{id}
    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        return studentService.getById(id);
    }

    // 3. 新增学生：POST /api/students
    @PostMapping
    public boolean create(@RequestBody Student student) {
        return studentService.save(student);
    }

    // 4. 修改学生：PUT /api/students/{id}
    @PutMapping("/{id}")
    public boolean update(@PathVariable Integer id,
                          @RequestBody Student student) {
        student.setId(id);          // 确保更新的是这个 id
        return studentService.updateById(student);
    }

    // 5. 删除学生：DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return studentService.removeById(id);
    }
}
