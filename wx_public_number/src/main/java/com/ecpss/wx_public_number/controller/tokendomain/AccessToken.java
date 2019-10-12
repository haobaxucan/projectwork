package com.ecpss.wx_public_number.controller.tokendomain;

import lombok.Data;

@Data
public class AccessToken {
    private String accessToken;
    private Long ExpireTime;
}
