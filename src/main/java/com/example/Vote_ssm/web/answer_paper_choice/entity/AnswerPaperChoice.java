package com.example.Vote_ssm.web.answer_paper_choice.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerPaperChoice implements Serializable {

    private Long answerChoiceId;
    private Long choiceId;
    private Long paperId;
    private Long userId;


}
