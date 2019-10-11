package com.ecpss.wx_public_number.controller.tokendomain;

import com.ecpss.wx_public_number.utils.HttpUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempleteMsgController {
     String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    private static  final String ACCESS_TOKEN="26_3j28-INbCmmY9q49Q2KclqvGvo2aTz2AV9iD7Z6RsHLPakQcwifQLaxT7VkHotQvRjgCLivZsUdFXM6znO1PZjhn1wM1b6FSxr4Q54kI5Sev2MX6FuM4Dk4HiiYE_lEDX4JJdH6gUeUDQIc7DEVfAEALMD";

    @RequestMapping("/temp")
    public String sendMsg(){
        url=url.replace("ACCESS_TOKEN",ACCESS_TOKEN);
//        data
        String data="{\n" +
                "           \"touser\":\"oFm7l1BFG8jNNV1xgwNwP0xM2nHQ\",\n" +
                "           \"template_id\":\"scX5gJWg_j_0-1QQc9cdsDyRF7TtLpNS3ZCP2u1rjeQ\",\n" +
                "           \"url\":\"https://www.baidu.com/\",  \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"改价变动！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"company\":{\n" +
                "                       \"value\":\"富农\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String s = HttpUtils.doPost(url, data, 5000);
        System.out.println(s);
        return  s;
    }

}
