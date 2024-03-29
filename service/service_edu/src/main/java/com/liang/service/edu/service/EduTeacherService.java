package com.liang.service.edu.service;

import com.liang.service.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author liang
 * @since 2022-06-22
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> selectTeacher(String count);
}
