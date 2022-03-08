package com.example.Vote_ssm.web.sys_question.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class SysQuestion implements Serializable {


   private Long questionId;
   private String questionTitle;
   private String  questionDesc;
   private String questionImg;
   private Integer questionOrder;
   private String questionStatus;

}
