package com.liang.service.edu.service.impl;

import com.liang.service.base.exceptionHandler.MyException;
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

    @Autowired
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

    @Override
    public CourseInfo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);

        // 封装到CourseInfoVo中
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(eduCourse, courseInfo);

        // 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfo.setDescription(courseDescription.getDescription());

        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new MyException(20001, "修改课程信息失败");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfo.getId());
        description.setDescription(courseInfo.getDescription());
        courseDescriptionService.updateById(description);
    }
}
