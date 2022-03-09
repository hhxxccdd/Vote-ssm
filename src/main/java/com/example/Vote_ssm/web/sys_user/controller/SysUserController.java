package com.example.Vote_ssm.web.sys_user.controller;


import com.example.Vote_ssm.common.ResultMap;
import com.example.Vote_ssm.common.ResultUtils;
import com.example.Vote_ssm.common.ResultVo;
import com.example.Vote_ssm.common.StatusCode;
import com.example.Vote_ssm.web.sys_user.entity.LoginParm;
import com.example.Vote_ssm.web.sys_user.entity.SysUser;
import com.example.Vote_ssm.web.sys_user.entity.UserParm;
import com.example.Vote_ssm.web.sys_user.service.SysUserService;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 系统用户控制器
 */
@Controller
@RequestMapping("/api/sysUser")
public class SysUserController {

   @Autowired
    private SysUserService sysUserService;

    /**
     * 获取全部用户
     */
    @GetMapping("/getUserList")
    @ResponseBody //返回Json格式
    public ResultVo getUserList(){

        List<SysUser> userList = sysUserService.getUserList();
        //构造返回值
//        ResultVo vo =new ResultVo("查询成功",200,userList);
//        vo.setMsg("查询成功");
//        vo.setCode(200);
//        vo.setData(userList);
        return ResultUtils.success("查询成功",userList);

    }

    /**
     * 用户管理页面
     */
    @GetMapping("/index")
    public String index(){
        return "system/user/user";
    }


    /**
     * 新增用户页面
     */
    @GetMapping("/addUI")
    public String addUI(){
        return "system/user/add";
    }

    /**
     * 新增用户
     * SysUser sysUser:通过SysUser接受前端传来的参数
     * 前端的提交字段需要和这个对象的属性一一对应
     */
    @PostMapping("/add")
    @ResponseBody
    public ResultVo add(SysUser sysUser){
        //验证参数是否为空
        if( StringUtils.isEmpty(sysUser.getUsername()) || StringUtils.isEmpty(sysUser.getPassword()) ){
             return ResultUtils.error("参数不能为空");
        }
       //根据用户的账户查询该账户是否被占用
        SysUser user = sysUserService.getUserName(sysUser.getUsername());
        if(user != null){
            return ResultUtils.error("该账户已经被占用");
        }
        boolean save = sysUserService.addUser(sysUser);
        if(save){ //返回true，说明新增成功
            return ResultUtils.success("新增用户成功!");
        }
        return ResultUtils.error("新增用户失败");
    }

    /**
     * 获取列表
     * 1：返回json
     * 2：返回值需要满足layui table的数据格式
     */
    @GetMapping("/list")
    @ResponseBody
    public ResultMap list(UserParm  parm){
        List<SysUser> list = sysUserService.getUserTableList(parm);
        //接受分页返回的数据
        PageInfo<SysUser> page = new PageInfo<>(list);
        //设置符合layui表格的数据
        return  ResultUtils.pageResult(StatusCode.SUCCESS_LAYUI_CODE,"查询成功",page.getList(),page.getTotal());
    }

    /**
     * 编辑用户界面
     */
    @GetMapping("/editUI")
    public String editUI(Integer userId, ModelMap map){
        //查询要编辑的用户信息
        SysUser user = sysUserService.getUSerById(userId);
        //把数据返回前端展示
        map.put("user",user);
        return "system/user/edit";
    }

    /**
     * 编辑用户的保存
     * 1、验证账号是否被占用
     * 2、更新
     * 3、返回json
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultVo edit(SysUser sysUser){
        //1.验证账号是否被占用
        SysUser user = sysUserService.getUserName(sysUser.getUsername());
        if(user != null && !user.getUserId().equals(sysUser.getUserId())){
            return ResultUtils.error("该账户已经被占用");
        }
        //更新
        boolean flag = sysUserService.updateById(sysUser);
        if(flag){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }

    /**
     * 删除用户
     *
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultVo delete(String userId){
        boolean flag = sysUserService.deleteById(userId);
        if(flag){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    /**
     * 用户注册页面
     */
    @GetMapping("/Login")
    public String Login(){
        return "login";
    }


    /**
     * 用户注册页面
     */
    @GetMapping("/register")
    public String register(){
        return "system/user/register";
    }

    /**
     * 用户注册
     */
    @PostMapping("/registerCommit")
    @ResponseBody
    public ResultVo registerCommit(LoginParm loginParm,HttpServletRequest request){
       //验证验证码是否正确
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!loginParm.getCode().equals(code)){
             return ResultUtils.error("验证码错误！");
        }
        //判断用户的账号是否被注册
        SysUser user = sysUserService.getUserName(loginParm.getUsername());
        if(user != null){
            return  ResultUtils.error("用户已经被注册");
        }
        SysUser sysUser =new SysUser();
        BeanUtils.copyProperties(loginParm,sysUser);
        sysUser.setStatus(1);
        boolean b = sysUserService.addUser(sysUser);
        if(b){
            return ResultUtils.success("注册成功");
        }
        return ResultUtils.error("注册失败");
    }

    /**
     * 用户登录
     */
    @PostMapping("/loginCommit")
    @ResponseBody
    public ResultVo loginCommit(LoginParm loginParm,HttpServletRequest request){
        //验证验证码是否正确
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!loginParm.getCode().equals(code)){
            return ResultUtils.error("验证码错误！");
        }
        SysUser sysUser =new SysUser();
        BeanUtils.copyProperties(loginParm,sysUser);
        SysUser login = sysUserService.login(sysUser);
        if(login != null){
            session.setAttribute("user",login);
            return ResultUtils.success("登录成功",login.getUserId());
        }

        return ResultUtils.error("用户名或密码错误");
    }

    /**
     * 投票统计
     */
    @GetMapping("/countList")
    public String countList(){
        return "system/question/countList";
    }


}
