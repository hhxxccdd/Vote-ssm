package com.example.Vote_ssm.web.sys_paper.mapper;


import com.example.Vote_ssm.web.sys_paper.entity.SysPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysPaperMapper {

    //新增试题
    boolean addPaper(@Param("paper")SysPaper paper);


    //删除试题
    boolean deletePaper(@Param("questionId")Long questionId);


    //根据试卷id查询题目列表
    List<SysPaper> listPaper(@Param("questionId")Long questionId);


}
