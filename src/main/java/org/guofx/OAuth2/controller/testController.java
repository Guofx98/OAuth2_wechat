package org.guofx.OAuth2.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import org.guofx.OAuth2.entity.WeChatUser;
import org.guofx.OAuth2.util.WechatUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@RestController
public class testController {
    @RequestMapping("/wxCheck")
    public String wxSignatureCheck(@RequestParam(value = "signature") String signature, @RequestParam(value = "timestamp") String timestamp, @RequestParam(value = "nonce") String nonce, @RequestParam(value = "echostr") String echostr){
        System.out.println("收到微信校验请求："+echostr);
        return echostr;
    }
    @GetMapping("/wxLogin")
    @ResponseBody
    public void wxLoginPage(HttpServletResponse response) throws Exception{
        String redirectUrl = URLEncoder.encode("https://5324781c24.eicp.vip/wxCallback","UTF-8");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e0e3743c09e869c&redirect_uri="+redirectUrl+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        response.setContentType("image/png");
        QrCodeUtil.generate(url,300,300,"jpg",response.getOutputStream());
    }
    @RequestMapping("/wxCallback")
    @ResponseBody
    public String pcCallback(String code, String state, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
        WeChatUser user = WechatUtil.getUserInfo(code);
        return JSON.toJSONString(user);
    }

}
