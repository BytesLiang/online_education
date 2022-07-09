package com.liang.service.edu.service;

import com.liang.service.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.service.edu.entity.vo.CourseInfo;
import com.liang.service.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfo courseInfo);

    CourseInfo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfo courseInfo);

    CoursePublishVo getPublishCourseInfo(String id);
}
