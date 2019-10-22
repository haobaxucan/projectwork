package com.ecpss.tkmybatis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class HttpClient {

    @Autowired(required = false)
    private RestTemplate restTemplate;

    public String get(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    public String exchange(String url, HttpEntity formEntity ,HttpMethod httpMethod ) throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(url, httpMethod,
                formEntity, String.class);
        if (response.getStatusCode()== HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public String post(String url, Map params) {
        String response = restTemplate.postForObject(url, String.class, String.class, params);
        return response;
    }

//    public ResponseDTO postForEntity(String url, HashMap params) {
//        ResponseEntity<ResponseDTO> response = restTemplate.postForEntity(url, ResponseDTO.class, String.class, params);
//        return response.getBody();
//    }

    /**
     * 设置请求头
     *
     * @param request
     * @return
     */
    public HttpHeaders getHeaders(HttpServletRequest request){
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        String contentType = request.getContentType();
        if ( null!=contentType && !contentType.isEmpty() && contentType.contains("multipart/form-data")){
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        }else{
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        }

        String token = request.getHeader("token");
        headers.add("token",token);
        // 设置token:php接口身份校验
        HttpSession session = request.getSession();
        if ( !session.isNew() && session.getAttribute("token")!=null){
            headers.add("token",session.getAttribute("token").toString());
           // session.removeAttribute("token");
        }
        return  headers;
    }

}
