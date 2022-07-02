package com.liang.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.liang.service.edu.entity.excel.SubjectData;
import com.liang.service.edu.listener.SubjectExcelListener;
import com.liang.service.edu.service.EduSubjectService;
import com.liang.service.edu.entity.EduSubject;
import com.liang.service.edu.mapper.EduSubjectMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author liang
 * @since 2022-06-30
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EduSubject> getAllSubject() {
        List<EduSubject> subjectList = baseMapper.selectList(null);

        return subjectList.stream()
                .filter(entity -> Objects.equals(entity.getParentId(), "0"))
                .peek((entity) -> entity.setChildren(getChildren(entity, subjectList)))
                .sorted(Comparator.comparingInt(EduSubject::getSort))
                .collect(Collectors.toList());
    }

    public List<EduSubject> getChildren(EduSubject root, List<EduSubject> list){
        return list.stream().filter(entity -> entity.getParentId().equals(root.getId()))
                //多级分类
//                .peek((entity) -> {
//                    entity.setChildren(getChildren(entity, list));
//                })
                .sorted(Comparator.comparingInt(EduSubject::getSort))
                .collect(Collectors.toList());
    }
}
