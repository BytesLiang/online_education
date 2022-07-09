package com.liang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.service.base.exceptionHandler.MyException;
import com.liang.service.edu.entity.EduChapter;
import com.liang.service.edu.entity.EduVideo;
import com.liang.service.edu.entity.vo.ChapterVo;
import com.liang.service.edu.entity.vo.VideoVo;
import com.liang.service.edu.mapper.EduChapterMapper;
import com.liang.service.edu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.service.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liang
 * @since 2022-07-02
 */
@Service
@Transactional
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    private EduVideoService videoService;

    @Autowired
    public void setVideoService(EduVideoService videoService) {
        this.videoService = videoService;
    }

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(chapterQueryWrapper);
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(videoQueryWrapper);
        return eduChapterList.stream()
                .sorted(Comparator.comparingInt(EduChapter::getSort))
                .map((chapter) -> {
                    ChapterVo chapterVo = new ChapterVo();
                    BeanUtils.copyProperties(chapter, chapterVo);
                    return chapterVo;
                })
                .peek(chapterVo -> chapterVo.setChildren(getVideoVoList(eduVideoList, chapterVo.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", chapterId);
        if(videoService.count(videoQueryWrapper) > 0){
            throw new MyException(20001, "包含有小节，不能删除");
        }
        return baseMapper.deleteById(chapterId) > 0;
    }

    private List<VideoVo> getVideoVoList(List<EduVideo> videoList, String chapterId) {
        return videoList.stream().filter(video -> video.getChapterId().equals(chapterId))
                .sorted(Comparator.comparingInt(EduVideo::getSort))
                .map(video -> {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    return videoVo;
                })
                .collect(Collectors.toList());
    }
}
