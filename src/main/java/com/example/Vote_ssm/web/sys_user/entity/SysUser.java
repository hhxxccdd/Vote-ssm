package com.example.Vote_ssm.web.sys_user.entity;

import lombok.Data;

import java.io.Serializable;


//Serializable：防止在传输中报错
@Data   //自动生成Get和Set方法
public class SysUser implements Serializable {

   private Long UserId;
   private String username;
   private String password;
   private String phone;
   private String email;
   private int status;
   private boolean isAdmin;

}
