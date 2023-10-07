package org.guofx.OAuth2.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.guofx.OAuth2.entity.TokenInfo;
import org.guofx.OAuth2.entity.WeChatUser;


public class WechatUtil {

    private static final String appid = "wx3e0e3743c09e869c";
    private static final String secret = "6c553d7557ffc863bf958b1318850b9a";

    public static WeChatUser getUserInfo(String code) throws Exception{
        System.out.println("code:"+code);
        HttpClient httpClient = HttpClients.createDefault();
        String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(tokenUrl);
        String responseResult = "";
        HttpResponse response = httpClient.execute(httpGet);
        if(response.getStatusLine().getStatusCode()==200){
            responseResult = EntityUtils.toString(response.getEntity(),"UTF-8");
        }
        System.out.println("获取accessToken返回结果："+responseResult);
        TokenInfo tokenInfo = JSON.parseObject(responseResult, TokenInfo.class);

        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+tokenInfo.getAccess_token()+"&openid="+tokenInfo.getOpenid()+"&lang=zh_CN";
        HttpGet httpGet1 = new HttpGet(userInfoUrl);
        HttpResponse response1 = httpClient.execute(httpGet1);
        if(response1.getStatusLine().getStatusCode()==200){
            responseResult = EntityUtils.toString(response1.getEntity(),"UTF-8");
        }
        System.out.println("获取用户信息"+responseResult);
        WeChatUser weChatUser = JSON.parseObject(responseResult, WeChatUser.class);
        return weChatUser;
    }
}
