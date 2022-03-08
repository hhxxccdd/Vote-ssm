package com.example.Vote_ssm.web.sys_question.service;

import com.example.Vote_ssm.web.sys_question.entity.QuestionParm;
import com.example.Vote_ssm.web.sys_question.entity.SysQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysQuestionService {

    //新增用户
    boolean addQuestion(SysQuestion sysQuestion);

    //表格查询
    List<SysQuestion> getTableList(QuestionParm parm);

    //编辑问卷
    boolean editQuestion(SysQuestion sysQuestion);

    //根据id查询
    SysQuestion getById(Long questionId);

    //根据id删除
    boolean deleteQuestion(Long questionId);

    //我的问卷列表
    List<SysQuestion>  getMyList(QuestionParm parm);

}
