package com.example.Vote_ssm.web.answer_question.mapper;


import com.example.Vote_ssm.web.answer_question.entity.AnswerQuestion;
import org.apache.ibatis.annotations.Param;

public interface AnswerQuestionMapper {

    //新增答卷
    boolean add(@Param("answerQuestion") AnswerQuestion answerQuestion);
    //判断是否答卷
    AnswerQuestion getAnswerById(@Param("questionId") Long questionId,@Param("userId") Long userId);



}
