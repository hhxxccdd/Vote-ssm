package com.example.Vote_ssm.web.sys_question.entity;

import com.example.Vote_ssm.web.sys_paper_choice.entity.ResultPaperChoice;
import lombok.Data;

import java.io.Serializable;

import java.io.Serializable;
import java.util.List;
@Data
public class ResultPaper implements Serializable {
    //试题id
    private Long paperId;
    //问卷id
    private Long questionId;
    //标题
    private String paperTitle;
    //试题类型
    private String paperType;
    private Long paperOrder;
    //单选和多选对应的选项
    private List<ResultPaperChoice> paperChoice;
}
