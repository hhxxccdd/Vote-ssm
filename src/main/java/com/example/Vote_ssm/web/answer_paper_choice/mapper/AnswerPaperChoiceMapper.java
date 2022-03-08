package com.example.Vote_ssm.web.answer_paper_choice.mapper;

import com.example.Vote_ssm.web.answer_paper_choice.entity.AnswerPaperChoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerPaperChoiceMapper {
    //新增问卷的答案
    boolean saveBatch(@Param("list")List<AnswerPaperChoice> list);


}
