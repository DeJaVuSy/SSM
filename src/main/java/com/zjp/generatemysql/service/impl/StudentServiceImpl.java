package com.zjp.generatemysql.service.impl;

import com.zjp.generatemysql.entity.Student;
import com.zjp.generatemysql.mapper.StudentMapper;
import com.zjp.generatemysql.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-12-28
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
