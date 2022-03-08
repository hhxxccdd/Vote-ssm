package com.example.Vote_ssm.web.sys_user.entity;


//Serializable：防止在传输中报错
import lombok.Data;

import java.io.Serializable;

@Data
public class UserParm implements Serializable {
    //当前的页数
    private int page;
    // 每页查询的条数
    private int limit;
    //搜索关键字
    private String phone;


}
