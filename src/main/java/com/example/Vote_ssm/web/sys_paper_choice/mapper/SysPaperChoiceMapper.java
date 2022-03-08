package com.example.Vote_ssm.web.sys_paper_choice.mapper;

import com.example.Vote_ssm.web.sys_paper_choice.entity.ResultPaperChoice;
import com.example.Vote_ssm.web.sys_paper_choice.entity.SysPaperChoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPaperChoiceMapper {
    //1、删除选项接口
    boolean deleteChoice(@Param("paperId")Long paperId);
    //2.批量保存选项接口
    boolean saveBatch(@Param("choices") List<SysPaperChoice> choices);
    //3.根据试题id查选项列表
    List<SysPaperChoice> listPaperChoice(@Param("paperId")Long paperId);
    //查询选项的票数统计
    List<ResultPaperChoice>  getListPaperChocie(@Param("questionId") Long questionId);

}
