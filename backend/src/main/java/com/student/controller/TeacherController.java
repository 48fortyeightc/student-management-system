package com.student.controller;

import com.student.entity.Teacher;
import com.student.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /** 1. 查询所有老师 - GET /api/teachers */
    @GetMapping
    public List<Teacher> list(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String subject) {
        return teacherService.list(name, subject);
    }

    /** 2. 按 id 查询单个老师 - GET /api/teachers/{id} */
    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Integer id) {
        return teacherService.getById(id);
    }

    /** 3. 新增老师 - POST /api/teachers */
    @PostMapping
    public boolean create(@RequestBody Teacher teacher) {
        return teacherService.save(teacher);
    }

    /** 4. 修改老师 - PUT /api/teachers/{id} */
    @PutMapping("/{id}")
    public boolean update(@PathVariable Integer id,
                          @RequestBody Teacher teacher) {
        teacher.setId(id);  // 以路径里的 id 为准
        return teacherService.updateById(teacher);
    }

    /** 5. 删除老师 - DELETE /api/teachers/{id} */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return teacherService.removeById(id);
    }
}
