package com.liang.service.edu.service.impl;

import com.liang.service.edu.entity.EduChapter;
import com.liang.service.edu.entity.vo.ChapterVo;
import com.liang.service.edu.mapper.EduChapterMapper;
import com.liang.service.edu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        return null;
    }
}
