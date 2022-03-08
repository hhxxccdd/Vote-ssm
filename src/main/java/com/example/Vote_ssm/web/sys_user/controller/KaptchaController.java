package com.example.Vote_ssm.web.sys_user.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/api/sys")
public class KaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @RequestMapping("/captcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码文字
        String text = defaultKaptcha.createText();
        //把文字放在session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        //像浏览器输出图片
        BufferedImage bi =defaultKaptcha.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi,"jpg",out);
        try{
             out.flush();
        }finally {
             out.close();
        }


    }

}
