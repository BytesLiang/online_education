package com.liang.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.service.base.exceptionHandler.MyException;
import com.liang.service.edu.entity.excel.SubjectData;
import com.liang.service.edu.entity.EduSubject;
import com.liang.service.edu.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new MyException(2001, "文件数据为空");
        }
        EduSubject existOneSubject = existOneSubject(subjectData.getOneSubjectName());
        if(existOneSubject == null){
            existOneSubject = new EduSubject().setParentId("0")
                    .setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        EduSubject existTwoSubject = existTwoSubject(subjectData.getTwoSubjectName(), existOneSubject.getId());
        if(existTwoSubject == null){
            existTwoSubject = new EduSubject().setParentId(existOneSubject.getId())
                    .setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    // 判断是否存在一级分类
    public EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", 0);
        return subjectService.getOne(queryWrapper);
    }

    // 判断是否存在二级分类
    public EduSubject existTwoSubject(String name, String pid){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", pid);
        return subjectService.getOne(queryWrapper);
    }
}
