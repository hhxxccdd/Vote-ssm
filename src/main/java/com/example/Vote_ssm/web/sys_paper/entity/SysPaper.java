package com.example.Vote_ssm.web.sys_paper.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class SysPaper implements Serializable {
    //主键
    private Long paperId;
    //问卷id
    private Long questionId;
    //试题标题
    private String paperTitle;
    //试题类型
    private String paperType;
    //试题型号
    private Long  paperOrder;
}
