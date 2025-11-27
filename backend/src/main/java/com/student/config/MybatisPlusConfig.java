package com.student.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.student.mapper")
public class MybatisPlusConfig {
    // 暂时不需要写东西，@Configuration + @MapperScan 即可
}
