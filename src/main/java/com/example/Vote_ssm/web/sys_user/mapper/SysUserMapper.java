package com.example.Vote_ssm.web.sys_user.mapper;

import com.example.Vote_ssm.web.sys_user.entity.SysUser;
import com.example.Vote_ssm.web.sys_user.entity.UserParm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {


   //查询所有用户
    List<SysUser> getUserList();
   //新增用户
    boolean addUser(@Param("user") SysUser user);
   //根据用户名查询账号是否被占用
    SysUser getUserByName(@Param("username") String username);
    //查询用户类表，带分页
    List<SysUser>  getUserTableList(@Param("parm") UserParm parm);
    //根据用户的id查询用户
    SysUser getUSerById(@Param("userId") Integer userId );
    //更新用户
    boolean updateById(@Param("user") SysUser user);
    //删除用户
    boolean deleteById(@Param("userId") String userId);
    //根据用户名和密码查询
    SysUser login(@Param("user") SysUser user);
}
