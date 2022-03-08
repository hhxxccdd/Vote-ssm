package com.example.Vote_ssm.web.sys_paper_choice.service.impl;

import com.example.Vote_ssm.web.sys_paper_choice.entity.ResultPaperChoice;
import com.example.Vote_ssm.web.sys_paper_choice.entity.SysPaperChoice;
import com.example.Vote_ssm.web.sys_paper_choice.mapper.SysPaperChoiceMapper;
import com.example.Vote_ssm.web.sys_paper_choice.service.SysPaperChoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPaperChoiceServiceImpl implements SysPaperChoiceService {

    @Resource
    private SysPaperChoiceMapper sysPaperChoiceMapper;


    @Override
    public boolean deleteChoice(Long paperId) {
        return sysPaperChoiceMapper.deleteChoice(paperId);
    }

    @Override
    public boolean saveBatch(List<SysPaperChoice> choices) {
        return sysPaperChoiceMapper.saveBatch(choices);
    }

    @Override
    public List<ResultPaperChoice> getListPaperChocie(Long questionId) {
        return sysPaperChoiceMapper.getListPaperChocie(questionId);
    }


}
