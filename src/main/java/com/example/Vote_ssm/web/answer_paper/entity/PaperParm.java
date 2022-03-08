package com.example.Vote_ssm.web.answer_paper.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class PaperParm implements Serializable {

   private Long paperId;
   private String paperType;
   private String paperValue;
   private String questionId;

}
