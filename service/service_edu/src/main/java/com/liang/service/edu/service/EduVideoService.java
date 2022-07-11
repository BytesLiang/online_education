package com.liang.service.edu.service;

import com.liang.service.edu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author liang
 * @since 2022-07-06
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String courseId);
}
