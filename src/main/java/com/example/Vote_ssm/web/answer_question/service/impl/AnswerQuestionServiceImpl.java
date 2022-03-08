package com.example.Vote_ssm.web.answer_question.service.impl;

import com.example.Vote_ssm.web.answer_paper.entity.CommitParm;
import com.example.Vote_ssm.web.answer_question.entity.AnswerQuestion;
import com.example.Vote_ssm.web.answer_question.mapper.AnswerQuestionMapper;
import com.example.Vote_ssm.web.answer_question.service.AnswerQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnswerQuestionServiceImpl implements AnswerQuestionService {

    @Resource
    private AnswerQuestionMapper answerQuestionMapper;

    @Override
    public AnswerQuestion getAnswerById(Long questionId, Long userId) {
        AnswerQuestion question = answerQuestionMapper.getAnswerById(questionId, userId);
        return question;
    }
}
