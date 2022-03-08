package com.example.Vote_ssm.web.sys_paper_choice.service;

import com.example.Vote_ssm.web.sys_paper_choice.entity.ResultPaperChoice;
import com.example.Vote_ssm.web.sys_paper_choice.entity.SysPaperChoice;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface SysPaperChoiceService {

    //1、删除选项接口
    boolean deleteChoice(Long paperId);
    //2.批量保存选项接口
    boolean saveBatch(List<SysPaperChoice> choices);
    //查询选项的票数统计
    List<ResultPaperChoice>  getListPaperChocie(Long questionId);

}
