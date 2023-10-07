package org.guofx.OAuth2.entity;

import lombok.Data;

@Data
public class WeChatUser {
    private String openid;
    private String nickname;
    private Integer sex;
    private String country;
    private String city;
    private String province;
    private String headimgurl;
    private String privilege;
    private String unionid;
}
