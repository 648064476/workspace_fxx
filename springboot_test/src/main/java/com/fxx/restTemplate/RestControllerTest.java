package com.fxx.restTemplate;


import com.fxx.dto.TakeDelivery;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestControllerTest {

    @GetMapping("get")
    public Object get(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.66.40/api/web/takeDelivery/page?limit=10&page=1&type=1";
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        headers.add("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0aGlzIGlzIHRlc3QgdG9rZW4iLCJhdWQiOiJBUFAiLCJpc3MiOiJTRVJWSUNFIiwidXNlck5hbWUiOiJhZG1pbiIsImV4cCI6MTU3NjkxMzkzMCwiaWF0IjoxNTc2NzQxMTMwfQ.5HPcTD_f4D54RU3EECMMJB8N5xrPRc3TszageCBWPlU");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return resEntity.getBody();
    }
}
