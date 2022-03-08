package com.example.Vote_ssm.web.answer_paper.service.impl;

import com.example.Vote_ssm.web.answer_paper.entity.AnswerPaper;
import com.example.Vote_ssm.web.answer_paper.entity.CommitParm;
import com.example.Vote_ssm.web.answer_paper.entity.PaperParm;
import com.example.Vote_ssm.web.answer_paper.mapper.AnswerPaperMapper;
import com.example.Vote_ssm.web.answer_paper.service.AnswerPaperService;
import com.example.Vote_ssm.web.answer_paper_choice.entity.AnswerPaperChoice;
import com.example.Vote_ssm.web.answer_paper_choice.mapper.AnswerPaperChoiceMapper;
import com.example.Vote_ssm.web.answer_question.entity.AnswerQuestion;
import com.example.Vote_ssm.web.answer_question.mapper.AnswerQuestionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnswerPaperServiceImpl implements AnswerPaperService {
    @Resource
    private AnswerQuestionMapper answerQuestionMapper;
    @Resource
    private AnswerPaperMapper answerPaperMapper;
    @Resource
    private AnswerPaperChoiceMapper answerPaperChoiceMapper;

    @Override
    @Transactional
    public void commitParm(CommitParm parm) {
        //1.保存答卷
        AnswerQuestion answerQuestion =new AnswerQuestion();
        answerQuestion.setUserId(parm.getUserId());
        answerQuestion.setQuestionId(parm.getQuestionId());
        answerQuestion.setCreateTime(new Date());
        answerQuestionMapper.add(answerQuestion);

        //2.保存答案
        //前端传来的答案
        List<PaperParm> paperList = parm.getPaperList();
        List<AnswerPaper> list = new ArrayList<>();
        //循环答案，放到List里面
        for (int i=0; i<paperList.size();i++){
             AnswerPaper paper=new AnswerPaper();
             paper.setPaperId(paperList.get(i).getPaperId());
             paper.setUserId(parm.getUserId());
             paper.setPaperType(paperList.get(i).getPaperType());
             paper.setPaperValue(paperList.get(i).getPaperValue());
             list.add(paper);
             //保存答案选项
            if(paperList.get(i).getPaperType().equals("1")){ //单选题
                 List<AnswerPaperChoice> choiceList =new ArrayList<>();
                 AnswerPaperChoice choice= new AnswerPaperChoice();
                 choice.setChoiceId(Long.valueOf(paperList.get(i).getPaperValue()));
                 choice.setPaperId(paperList.get(i).getPaperId());
                 choice.setUserId(parm.getUserId());
                 choiceList.add(choice);
                 answerPaperChoiceMapper.saveBatch(choiceList);
            }
            if(paperList.get(i).getPaperType().equals("2")){  //多选题
                List<AnswerPaperChoice> choiceList =new ArrayList<>();
                String regs=",";
                String[] split = paperList.get(i).getPaperValue().split(regs);
                for(int j=0 ;j<split.length;j++){
                    AnswerPaperChoice choice= new AnswerPaperChoice();
                    choice.setChoiceId(Long.valueOf(split[j]));
                    choice.setPaperId(paperList.get(i).getPaperId());
                    choice.setUserId(parm.getUserId());
                    choiceList.add(choice);
                }
                answerPaperChoiceMapper.saveBatch(choiceList);
            }
        }
        //批量保存答案
        answerPaperMapper.saveBatch(list);
    }
}
