package com.example.Vote_ssm.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * layui table返回值
 * @param <T>
 */
@Data
@AllArgsConstructor
public class ResultMap<T> {
    //code要为0
    private int code;

    private String msg;

    private T data;

    private long count;

}