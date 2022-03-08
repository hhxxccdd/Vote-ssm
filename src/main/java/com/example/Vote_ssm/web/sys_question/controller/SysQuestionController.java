package com.example.Vote_ssm.web.sys_question.controller;


import com.example.Vote_ssm.common.ResultMap;
import com.example.Vote_ssm.common.ResultUtils;
import com.example.Vote_ssm.common.ResultVo;
import com.example.Vote_ssm.common.StatusCode;
import com.example.Vote_ssm.web.sys_paper.entity.SysPaperParm;
import com.example.Vote_ssm.web.sys_paper.service.SysPaperService;
import com.example.Vote_ssm.web.sys_paper_choice.entity.ResultPaperChoice;
import com.example.Vote_ssm.web.sys_paper_choice.service.SysPaperChoiceService;
import com.example.Vote_ssm.web.sys_question.entity.QuestionParm;
import com.example.Vote_ssm.web.sys_question.entity.ResultPaper;
import com.example.Vote_ssm.web.sys_question.entity.SysQuestion;
import com.example.Vote_ssm.web.sys_question.service.SysQuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/sysQuestion")
public class SysQuestionController {

    @Autowired
    private SysQuestionService sysQuestionService;

    @Autowired
    private SysPaperChoiceService sysPaperChoiceService;

    @Autowired
    private SysPaperService sysPaperService;

    /**
     * 问卷列表页
     */
    @GetMapping("/index")
   public String index(){

       return "/system/question/index";
   }


    /**
     * 新增页面
     */
    @GetMapping("/addUI")
    public String addUI(){
       return "system/question/add";
    }



    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping("/add")
   public ResultVo add(SysQuestion sysQuestion){


        boolean flag = sysQuestionService.addQuestion(sysQuestion);
        if(flag){
            return ResultUtils.success("新增成功");
        }
        return  ResultUtils.error("新增失败");

    }

    /**
     * 列表查询
     */
    @GetMapping("/list")
    @ResponseBody
    public ResultMap list(QuestionParm parm){
        List<SysQuestion> list = sysQuestionService.getTableList(parm);
        PageInfo<SysQuestion> pageInfo=new PageInfo<>(list);
        //返回code=0 返回的数据需要从pageInFo里面获取
        return ResultUtils.pageResult(StatusCode.SUCCESS_LAYUI_CODE,"查询成功",pageInfo.getList(),pageInfo.getTotal());

    }

    /**
     * 编辑问卷视图
     */
    @GetMapping("/editUI")
    public  String editUI(Long questionId, ModelMap map){
        SysQuestion user = sysQuestionService.getById(questionId);
        map.put("user",user);
        return "system/question/edit";
    }

    /**
     *编辑
     */
    @PostMapping("/edit")
    @ResponseBody
    public  ResultVo edit(SysQuestion sysQuestion){
        boolean b = sysQuestionService.editQuestion(sysQuestion);
        if(b){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo delete(Long questionId){
        boolean b = sysQuestionService.deleteQuestion(questionId);
        if(b){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    /**
     *普通用户的问卷列表页
     */
    @GetMapping("commonList")
    public String commonList(){
        return "system/question/commonList";
    }

    /**
     * 我的问卷列表页
     */
    @GetMapping("/myListUI")
    public String myListUI(){
        return "system/question/myList";
    }

    /**
     * 我的问卷列表数据
     */
    @GetMapping("/myList")
    @ResponseBody
    public ResultMap myList(QuestionParm parm){
        List<SysQuestion> list = sysQuestionService.getMyList(parm);
        PageInfo<SysQuestion> pageInfo=new PageInfo<>(list);
        //返回code=0 返回的数据需要从pageInFo里面获取
        return ResultUtils.pageResult(StatusCode.SUCCESS_LAYUI_CODE,"查询成功",pageInfo.getList(),pageInfo.getTotal());

    }


    /**
     * 统计分析
     */
    @GetMapping("/lookList")
    public String LookList(Long questionId,String questionTitle,ModelMap map){
        map.put("questionId",questionId);
        map.put("questionTitle",questionTitle);
        return "system/question/lookList";
    }

    /**
     * 打印页面
     */
    @GetMapping("/count")
    public String count(Long questionId,String questionTitle,ModelMap map){
        map.put("questionId",questionId);
        map.put("questionTitle",questionTitle);
        return "system/question/count";
    }

    /**
     *
     */
    @GetMapping("/getTotalList")
    @ResponseBody
    public ResultVo getTotalList(Long questionId){
      //根据问卷id查询所有的试题
        List<SysPaperParm> paperList = sysPaperService.getPaperList(questionId);
        //存放返回值
        List<ResultPaper> resultList =new ArrayList<>();
        //查询试题对应的选项
        List<ResultPaperChoice> choiceList = sysPaperChoiceService.getListPaperChocie(questionId);
        if(paperList.size()>0){
            for (int i=0 ;i<paperList.size();i++){
                //定义返回值
                ResultPaper parm = new ResultPaper();
                //定义存放试题选项的容器
                List<ResultPaperChoice> paperChoices = new ArrayList<>();
                //获取用户的id
                Long paperId = paperList.get(i).getPaperId();
                choiceList.stream().filter(item -> item.getPaperId().equals(paperId)).forEach(item ->{
                    ResultPaperChoice choice =new ResultPaperChoice();
                    BeanUtils.copyProperties(item,choice);
                    //把选项添加到试题
                    paperChoices.add(choice);
                });
                BeanUtils.copyProperties(paperList.get(i),parm);
                parm.setPaperChoice(paperChoices);
                resultList.add(parm);
            }
        }
        return ResultUtils.success("查询成功",resultList);
    }

}
