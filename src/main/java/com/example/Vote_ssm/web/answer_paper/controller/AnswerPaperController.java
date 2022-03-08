package com.example.Vote_ssm.web.answer_paper.controller;


import com.example.Vote_ssm.common.ResultUtils;
import com.example.Vote_ssm.common.ResultVo;
import com.example.Vote_ssm.web.answer_paper.entity.CommitParm;
import com.example.Vote_ssm.web.answer_paper.service.AnswerPaperService;
import com.example.Vote_ssm.web.answer_question.entity.AnswerQuestion;
import com.example.Vote_ssm.web.answer_question.service.AnswerQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/answerPaper")
public class AnswerPaperController {
    @Autowired
    private AnswerPaperService answerPaperService;
    @Autowired
    private AnswerQuestionService answerQuestionService;

    @RequestMapping("/saveCommit")
    @ResponseBody
    public ResultVo saveCommit(@RequestBody CommitParm parm){
        //判断是否已经答卷
        AnswerQuestion answer = answerQuestionService.getAnswerById(parm.getQuestionId(), parm.getUserId());
        if(answer != null){
            return ResultUtils.error("感谢您的参与,请不要重复答卷");
        }
        answerPaperService.commitParm(parm);
        return ResultUtils.success("提交成功");
    }




}
