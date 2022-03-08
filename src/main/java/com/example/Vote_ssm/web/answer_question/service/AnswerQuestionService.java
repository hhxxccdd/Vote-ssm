package com.example.Vote_ssm.web.answer_question.service;

import com.example.Vote_ssm.web.answer_question.entity.AnswerQuestion;
import org.apache.ibatis.annotations.Param;

public interface AnswerQuestionService {

    AnswerQuestion getAnswerById(Long questionId,Long userId);
}
