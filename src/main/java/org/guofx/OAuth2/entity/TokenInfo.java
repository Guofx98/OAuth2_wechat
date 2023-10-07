package org.guofx.OAuth2.entity;

import lombok.Data;

@Data
public class TokenInfo {
    private String access_token;
    private String openid;
    private Integer expires_in;
    private String refresh_token;
    private String scope;
    private String is_snapshotuser;
    private String unionid;
}
