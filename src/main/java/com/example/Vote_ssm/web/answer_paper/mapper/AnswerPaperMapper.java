package com.example.Vote_ssm.web.answer_paper.mapper;


import com.example.Vote_ssm.web.answer_paper.entity.AnswerPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerPaperMapper {

    boolean saveBatch(@Param("list")List<AnswerPaper> list);


}
