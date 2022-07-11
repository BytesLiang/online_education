package com.liang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liang.service.base.exceptionHandler.MyException;
import com.liang.service.edu.entity.EduCourse;
import com.liang.service.edu.entity.EduCourseDescription;
import com.liang.service.edu.entity.EduVideo;
import com.liang.service.edu.entity.vo.CourseInfo;
import com.liang.service.edu.entity.vo.CoursePublishVo;
import com.liang.service.edu.entity.vo.CourseQuery;
import com.liang.service.edu.mapper.EduCourseMapper;
import com.liang.service.edu.service.EduChapterService;
import com.liang.service.edu.service.EduCourseDescriptionService;
import com.liang.service.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.service.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


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
    private EduChapterService chapterService;
    private EduVideoService videoService;

    @Autowired
    public void setCourseDescriptionService(EduCourseDescriptionService courseDescriptionService) {
        this.courseDescriptionService = courseDescriptionService;
    }

    @Autowired
    public void setChapterService(EduChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Autowired
    public void setVideoService(EduVideoService videoService) {
        this.videoService = videoService;
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

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    public Map<String, Object> queryByCondition(long current, long limit, CourseQuery courseQuery) {
        Page<EduCourse> coursePage = new Page<>(current, limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        Integer status = courseQuery.getStatus();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();
        wrapper.like(!StringUtils.isEmpty(title), "title", title);
        wrapper.eq(!StringUtils.isEmpty(status), "status", status);
        wrapper.eq(!StringUtils.isEmpty(subjectParentId), "subject_parent_id", subjectParentId);
        wrapper.eq(!StringUtils.isEmpty(subjectId), "subject_id", subjectId);
        switch (courseQuery.getSort()) {
            case "count":
                wrapper.orderByDesc("buy_count");
                break;
            case "price":
                wrapper.orderByAsc("price");
                break;
            default:
                wrapper.orderByDesc("gmt_create");
        }
        wrapper.ge(!StringUtils.isEmpty(begin), "gmt_create", begin);
        wrapper.le(!StringUtils.isEmpty(end), "gmt_create", end);
        baseMapper.selectPage(coursePage, wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("total", coursePage.getTotal());
        map.put("rows", coursePage.getRecords());
        return map;
    }

    @Override
    public void removeCourse(String id) {
        videoService.removeByCourseId(id);
        chapterService.removeByCourseId(id);
        courseDescriptionService.removeById(id);
        int delete = baseMapper.deleteById(id);
        if(delete == 0){
            throw new MyException(20001, "删除课程失败");
        }
    }
}
