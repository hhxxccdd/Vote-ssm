package com.example.Vote_ssm.web.answer_paper.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerPaper implements Serializable {

       private Long answerPaperId;
       private Long paperId;
       private Long userId;
       private String paperType;
       private String paperValue;

}
