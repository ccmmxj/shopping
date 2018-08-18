package com.shop.server.client;

import com.shop.server.client.impl.TestClientImpl;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name = "edu", fallback = TestClientImpl.class,configuration = FeignClientsConfiguration.class)
public interface TestClient {
//    @GetMapping("manage/eduLearnAll")
//    List<ManageCard> eduLearnAll();
//    @GetMapping(value = "/eduLearnOne")
//    ManageCard eduLearnOne(Long id);
}
