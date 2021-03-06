package com.ecpss.tkmybatis.controller.json;

import com.alibaba.fastjson.JSONObject;
import com.ecpss.tkmybatis.utils.HttpClient;
import com.ecpss.tkmybatis.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.00
 * @date 2019/10/22
 */
@Controller
@Slf4j
public class JsonController {
    @Autowired(required = false)
    RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/json")
    /**
     * [{"age":12,"name":"xx"},{"age":123,"name":"jie"}]
     */
    public String rest(@RequestBody List<Person> personList) {
        System.out.println(personList);
        return "success";
    }

    @Autowired
    private HttpClient httpClient;

    /**
     * 工具请求
     */
    @ResponseBody
    @RequestMapping(value = "/http", method = RequestMethod.POST)
    public String httpClients() throws Exception {
//        log.info("开始请求--------");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> httpEntity = new HttpEntity<>("[{\"age\":12,\"name\":\"xx\"},{\"age\":123,\"name\":\"jie\"}]", headers);
//        httpClient.exchange("localhost:80/json", httpEntity, HttpMethod.POST);

        List<Person> lists = new ArrayList<>();
        Person person1 = new Person();
        person1.setName("xx").setAge(12);
        Person person3 = new Person();
        person3.setName("jie").setAge(123);

        lists.add(person3);
        lists.add(person1);

//        HttpClientUtil.sendPostRequest("http://localhost:80/json", JSONObject.toJSONString(lists));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONObject.toJSONString(lists),headers);
        httpClient.exchange("http://localhost:80/json", httpEntity, HttpMethod.POST);
        return "success";

    }


    @ResponseBody
    @RequestMapping(value = "/psingle", method = RequestMethod.POST)
    public String reqstr(@RequestBody Person person) {
        System.out.println(person.getName());

        Integer age=(person.getAge()==null)?0:person.getAge();
        System.out.println("age"+age);
        return "s";
    }

    /**
     * 请求 localhost:80/phttp
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/phttp", method = RequestMethod.POST)
    public String reqHttp() throws Exception {
        Person person = new Person();
        person.setName("xc").setAge(12);
        String s = HttpClientUtil.sendPostRequest("http://localhost:80/psingle", JSONObject.toJSONString(person));

        return s;
    }

    @ResponseBody
    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public String test() throws Exception {
        List<MscAccountInfoDTO> lists=new ArrayList<>();
        MscAccountInfoDTO mscAccountInfoDTO2 = new MscAccountInfoDTO().
                setOpenId("ofxzNs-qFdXtrqMM4lO9vyigezEQ1").setMessageContent("------2222-----");
        MscAccountInfoDTO mscAccountInfoDTO1 = new MscAccountInfoDTO().
                setOpenId("ofxzNs-qFdXtrqMM4lO9vyigezEQ").setMessageContent("------2222-----");
        MscAccountInfoDTO mscAccountInfoDTO = new MscAccountInfoDTO().
                setOpenId("ofxzNs5GfJGyWANvibwe9V-ZSiVE").setMessageContent("---111");
        lists.add(mscAccountInfoDTO1);
        lists.add(mscAccountInfoDTO);

        String json = JSONObject.toJSONString(lists);
        String resp = HttpClientUtil.sendPostRequest("http://192.168.0.44:9090/wechatmessage",json);
        return resp;
    }


    @ResponseBody
    @RequestMapping(value = "/inmail", method = RequestMethod.POST)

    public String test1() throws Exception {
        SendMscContractMessageContentDTO contractMessageContentDTO=new SendMscContractMessageContentDTO();
        contractMessageContentDTO.setGood_name("test");
        String json = JSONObject.toJSONString(contractMessageContentDTO);

        MscSendTaskEntity mscSendTaskEntity=new MscSendTaskEntity();
        mscSendTaskEntity.setCustomerId(1).setContent(json).setContentType("INMAIL");
        String toJSONString = JSONObject.toJSONString(mscSendTaskEntity);
        String resp = HttpClientUtil.sendPostRequest("http://192.168.0.44:9090/inmail/send",toJSONString);
        return resp;
    }
}
