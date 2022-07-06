package com.liang.service.edu.service.impl;

import com.liang.service.edu.entity.EduCourse;
import com.liang.service.edu.entity.EduCourseDescription;
import com.liang.service.edu.entity.vo.CourseInfo;
import com.liang.service.edu.mapper.EduCourseMapper;
import com.liang.service.edu.service.EduCourseDescriptionService;
import com.liang.service.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    private EduCourseDescriptionService courseDescriptionService;

    public void setCourseDescriptionService(EduCourseDescriptionService courseDescriptionService) {
        this.courseDescriptionService = courseDescriptionService;
    }

    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        this.save(eduCourse);

        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(eduCourse.getId());
        courseDescription.setDescription(courseInfo.getDescription());
        courseDescriptionService.save(courseDescription);
        return eduCourse.getId();
    }
}
