package com.example.controller;

import javax.servlet.http.HttpSession;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.util.Others;
import com.google.code.kaptcha.Producer;  

@Controller  
@RequestMapping("/kaptcha/*")  
public class CaptchaController {  

    @Autowired
    private Producer captchaProducer;

    @RequestMapping  
    public @ResponseBody String getKaptchaImage(HttpSession session) throws Exception {  
        String b64 = Others.getCaptchaImg(session,captchaProducer);
        return b64;  
    }
  
}
