package com.liang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.service.base.exceptionHandler.MyException;
import com.liang.service.edu.client.VodClient;
import com.liang.service.edu.entity.EduVideo;
import com.liang.service.edu.mapper.EduVideoMapper;
import com.liang.service.edu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author liang
 * @since 2022-07-06
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    private VodClient vodClient;

    @Autowired
    public void setVodClient(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.select("video_source_id");
        List<String> videoList = baseMapper.selectList(wrapper).stream()
                .filter(video -> !StringUtils.isEmpty(video))
                .map(EduVideo::getVideoSourceId)
                .collect(Collectors.toList());
        vodClient.removeBatch(videoList);
        int delete = baseMapper.delete(wrapper);
        if(delete == 0){
            throw new MyException(20001, "删除小节失败");
        }
    }

    @Override
    public void removeVideo(String id) {
        String videoSourceId = baseMapper.selectById(id).getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId))
            vodClient.remove(videoSourceId);
        int delete = baseMapper.deleteById(id);
        if(delete == 0){
            throw new MyException(20001, "删除小节失败");
        }
    }
}
