package com.example.Vote_ssm.web.sys_paper.service;

import com.example.Vote_ssm.web.sys_paper.entity.SysPaper;
import com.example.Vote_ssm.web.sys_paper.entity.SysPaperParm;

import java.util.List;

public interface SysPaperService {

    void savePaper(List<SysPaperParm> sysPaperParm);

    //新增试题
    boolean addPaper(SysPaper paper);


    //删除试题
    boolean deletePaper(Long questionId);

    //根据问卷id查询试题列表回显
    List<SysPaperParm> getPaperList(Long questionId);



}
