package com.ld.sbjwtjpa.api.restTemplate.controller;

import com.ld.sbjwtjpa.business.sys.account.model.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * 利用RestTemplate实现rest接口调用rest接口
 */
@RestController
@RequestMapping("/api")
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getForEntity")
    public List<AccountModel> getAll2() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost/getAll", List.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int code = statusCode.value();
        List<AccountModel> list = responseEntity.getBody();
        System.out.println(list.toString());
        return list;
    }

    //有参数的 getForEntity 请求,参数列表
    @RequestMapping("getForEntity/{id}")
    public AccountModel getById2(@PathVariable(name = "id") String id) {

        ResponseEntity<AccountModel> responseEntity = restTemplate.getForEntity("http://localhost/get/{id}", AccountModel.class, id);
        AccountModel AccountModel = responseEntity.getBody();
        return AccountModel;
    }

    //有参数的 get 请求,使用map封装参数
    @RequestMapping("getForEntity/{id}")
    public AccountModel getById4(@PathVariable(name = "id") String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        ResponseEntity<AccountModel> responseEntity = restTemplate.getForEntity("http://localhost/get/{id}", AccountModel.class, map);
        AccountModel AccountModel = responseEntity.getBody();

        return AccountModel;
    }

    //有参数的 get 请求,使用map封装请求参数
    @RequestMapping("get3/{id}")
    public AccountModel getById3(@PathVariable(name = "id") String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);

        AccountModel AccountModel = restTemplate.getForObject("http://localhost/get/{id}", AccountModel.class, map);

        return AccountModel;
    }

    //post 请求,提交 AccountModel 对像

    @RequestMapping("saveUser")
    public String save(AccountModel AccountModel) {

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost/save", AccountModel, String.class);
        String body = responseEntity.getBody();

        return body;

    }

    // 有参数的 postForEntity 请求
    @RequestMapping("saveUserByType/{type}")
    public String save2(AccountModel AccountModel, @PathVariable("type") String type) {

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost/saveByType/{type}", AccountModel, String.class, type);
        String body = responseEntity.getBody();

        return body;

    }

    // 有参数的 postForEntity 请求,使用map封装
    @RequestMapping("saveUserByType2/{type}")
    public String save3(AccountModel AccountModel, @PathVariable("type") String type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);


        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost/saveByType/{type}", AccountModel, String.class, map);
        String body = responseEntity.getBody();

        return body;

    }
}
