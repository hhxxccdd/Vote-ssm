package com.example.Vote_ssm.web.sys_question.service.impl;

import com.example.Vote_ssm.web.sys_question.entity.QuestionParm;
import com.example.Vote_ssm.web.sys_question.entity.SysQuestion;
import com.example.Vote_ssm.web.sys_question.mapper.SysQuestionMapper;
import com.example.Vote_ssm.web.sys_question.service.SysQuestionService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SysQuestionServiceImpl implements SysQuestionService {

    @Resource
    private SysQuestionMapper sysQuestionMapper;


    @Override
    public boolean addQuestion(SysQuestion sysQuestion) {
        return sysQuestionMapper.addQuestion(sysQuestion);
    }

    @Override
    public List<SysQuestion> getTableList(QuestionParm parm) {
        //设置分页的参数，一定要在查询前设置
        PageHelper.startPage(parm.getPage(),parm.getLimit());
        return sysQuestionMapper.getTableList(parm.getQuestionTitle());
    }

    @Override
    public boolean editQuestion(SysQuestion sysQuestion) {
        return sysQuestionMapper.editQuestion(sysQuestion);
    }

    @Override
    public SysQuestion getById(Long questionId) {
        return sysQuestionMapper.getById(questionId);
    }

    @Override
    public boolean deleteQuestion(Long questionId) {
        return sysQuestionMapper.deleteQuestion(questionId);
    }

    @Override
    public List<SysQuestion> getMyList(QuestionParm parm) {
        //设置分页参数,一定要在查询之前
        PageHelper.startPage(parm.getPage(),parm.getLimit());
        List<SysQuestion> list = sysQuestionMapper.getMyList(parm.getQuestionTitle(),parm.getUserId());
        return list;
    }

}
