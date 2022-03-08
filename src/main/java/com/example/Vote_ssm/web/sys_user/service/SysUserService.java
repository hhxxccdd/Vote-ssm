package com.example.Vote_ssm.web.sys_user.service;

import com.example.Vote_ssm.web.sys_user.entity.SysUser;
import com.example.Vote_ssm.web.sys_user.entity.UserParm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserService {

    //查询所有用户
    List<SysUser> getUserList();

    //新增用户
    boolean addUser(SysUser sysUser);

    //查询用户名是否被占用
    SysUser getUserName(String username);

    //分页查询用户列表
    List<SysUser>  getUserTableList( UserParm parm);

    //根据用户id查询用户
    SysUser getUSerById( Integer userId );

    //更新用户
    boolean updateById( SysUser user);

    //删除用户
    boolean deleteById(String userId);

    //用户登录
    SysUser login(SysUser user);

}
