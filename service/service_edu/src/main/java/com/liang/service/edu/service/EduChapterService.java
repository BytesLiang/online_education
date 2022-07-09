package com.liang.service.edu.service;

import com.liang.service.edu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideo(String courseId);

    boolean deleteChapter(String chapterId);
}
