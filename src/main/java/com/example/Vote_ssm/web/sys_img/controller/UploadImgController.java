package com.example.Vote_ssm.web.sys_img.controller;


import com.example.Vote_ssm.common.StatusCode;
import com.example.Vote_ssm.web.sys_img.entity.ResultImg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 图片上传控制器
 */
@RestController
@RequestMapping("/api/upload")
public class UploadImgController {

    @RequestMapping("/uploadImage")
     public ResultImg uploadImg(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        //定义返回的url
         String Url=null;
        //获取文件的名字
         String fileName = file.getOriginalFilename();
        //获取文件的扩展名
         String fileEXtenionName =fileName.substring(fileName.indexOf("."));
        //获取上传的路径
         String path=request.getSession().getServletContext().getRealPath("/WEB-INF/staticImg");
        //创建文件,如果不存在，创建
         File fileDeir = new File(path);
         if(!fileDeir.exists()){
             //设置文件的权限
             fileDeir.setWritable(true);
             //如果不存在，创建文件
             fileDeir.mkdirs();
         }
         //给文件取一个新得名字
         String newname = UUID.randomUUID().toString()+fileEXtenionName;
         File targetFile = new File(path,newname);
         try {
             //上传
             file.transferTo(targetFile);
             Url ="/staticImg/"+targetFile.getName();
         } catch (IOException e) {
             e.printStackTrace();
             return null;
         }

         //返回值组装
         ResultImg result =new ResultImg();
         result.setCode(StatusCode.SUCCESS_LAYUI_CODE);
         result.setMsg("图片上传成功");
         Map<String,String> map= new HashMap<>();
         map.put("src",Url);
         result.setData(map);
         return result;
     }

     

}
