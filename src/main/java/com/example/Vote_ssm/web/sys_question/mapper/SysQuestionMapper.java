package com.example.Vote_ssm.web.sys_question.mapper;

import com.example.Vote_ssm.web.sys_question.entity.SysQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysQuestionMapper {
    //新增文件
    boolean addQuestion(@Param("sysQuestion") SysQuestion sysQuestion);

    //表格查询
    List<SysQuestion> getTableList(@Param("questionTitle")String questionTitle);

    //编辑问卷
    boolean editQuestion(@Param("sysQuestion")SysQuestion sysQuestion);

    //根据id查询
    SysQuestion getById(@Param("questionId") Long questionId);

    //根据id删除
    boolean deleteQuestion(@Param("questionId") Long questionId);

    //我的问卷
    List<SysQuestion>  getMyList(@Param("questionTitle") String questionTitle,@Param("userId") Long userId);

}
