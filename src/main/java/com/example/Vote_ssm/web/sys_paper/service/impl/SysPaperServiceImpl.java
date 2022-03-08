package com.example.Vote_ssm.web.sys_paper.service.impl;

import com.example.Vote_ssm.web.sys_paper.entity.SysPaper;
import com.example.Vote_ssm.web.sys_paper.entity.SysPaperParm;
import com.example.Vote_ssm.web.sys_paper.mapper.SysPaperMapper;
import com.example.Vote_ssm.web.sys_paper.service.SysPaperService;
import com.example.Vote_ssm.web.sys_paper_choice.entity.SysPaperChoice;
import com.example.Vote_ssm.web.sys_paper_choice.mapper.SysPaperChoiceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysPaperServiceImpl implements SysPaperService {

    @Resource
    private SysPaperMapper sysPaperMapper;

    @Resource
    private SysPaperChoiceMapper sysPaperChoiceMapper;

    @Override
    public void savePaper(List<SysPaperParm> sysPaperParm) {
         //先删除，后保存
       for (int i =0 ;i < sysPaperParm.size(); i++){
           //判断是否是单选题和多选题，是，需要删除对应的选项
           if(sysPaperParm.get(i).getPaperType().equals("1") || sysPaperParm.get(i).getPaperType().equals("2")){
               //删除选项,根据试题的id删除对应的选项
               if(!StringUtils.isEmpty(sysPaperParm.get(i).getPaperId())){
                   sysPaperChoiceMapper.deleteChoice(sysPaperParm.get(i).getPaperId());
               }
           }
           //删除试题
           sysPaperMapper.deletePaper(sysPaperParm.get(i).getQuestionId());
       }
       //保存试题
        for(int k=0 ; k<sysPaperParm.size(); k++){
           //先保存试题
            SysPaper paper =new SysPaper();
            BeanUtils.copyProperties(sysPaperParm.get(k),paper);
            //设置序号
            paper.setPaperOrder(k+1L);
            sysPaperMapper.addPaper(paper);
            //判断是否是单选题和多选题,如果是，则保存选项
            if(sysPaperParm.get(k).getPaperType().equals("1") || sysPaperParm.get(k).getPaperType().equals("2")){
                //设置对应的试题id
                sysPaperParm.get(k).getPaperChoice().forEach(item ->{
                    item.setPaperId(paper.getPaperId());
                });
                sysPaperChoiceMapper.saveBatch(sysPaperParm.get(k).getPaperChoice());
            }
        }


    }

    @Override
    public boolean addPaper(SysPaper paper) {
        return false;
    }

    @Override
    public boolean deletePaper(Long questionId) {
        return false;
    }

    @Override
    public List<SysPaperParm> getPaperList(Long questionId) {
        //根据试卷id查询试题列表
        List<SysPaper> paperList = sysPaperMapper.listPaper(questionId);
        List<SysPaperParm> list =new ArrayList<>();
        if(paperList != null && paperList.size()>0){
            //循环试题列表
            for (int i =0 ;i <paperList.size();i++){
               SysPaperParm sysPaperParm =new SysPaperParm();
               BeanUtils.copyProperties(paperList.get(i),sysPaperParm);
               //判断是否是单选或者多选，如果是，则添加paperChoice选项列表
                List<SysPaperChoice> choiceList = sysPaperChoiceMapper.listPaperChoice(paperList.get(i).getPaperId());
                //设置试题对应的选项
                sysPaperParm.setPaperChoice(choiceList);
                list.add(sysPaperParm);
            }
        }

        return list;
    }
}
