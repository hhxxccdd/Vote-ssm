package com.example.Vote_ssm.web.sys_user.service.impl;

import com.example.Vote_ssm.web.sys_user.entity.SysUser;
import com.example.Vote_ssm.web.sys_user.entity.UserParm;
import com.example.Vote_ssm.web.sys_user.mapper.SysUserMapper;
import com.example.Vote_ssm.web.sys_user.service.SysUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    //@Autowired
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> getUserList() {
        return sysUserMapper.getUserList();
    }

    @Override
    public boolean addUser(SysUser sysUser) {
        return sysUserMapper.addUser(sysUser);
    }

    @Override
    public SysUser getUserName(String username) {
        return sysUserMapper.getUserByName(username);
    }

    @Override
    public List<SysUser> getUserTableList(UserParm parm) {
        //设置分页参数，设置分页参数一定要在sql查询之前
        PageHelper.startPage(parm.getPage(),parm.getLimit());
        return sysUserMapper.getUserTableList(parm);

    }

    @Override
    public SysUser getUSerById(Integer userId) {
        return sysUserMapper.getUSerById(userId);
    }

    @Override
    public boolean updateById(SysUser user) {
        return sysUserMapper.updateById(user);
    }

    @Override
    public boolean deleteById(String userId) {
        return sysUserMapper.deleteById(userId);
    }

    @Override
    public SysUser login(SysUser user) {
        return sysUserMapper.login(user);
    }
}
