package com.classeye.universityservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sejja
 **/
@FeignClient(name = "auth-service", url = "http://localhost:8088/AUTH-SERVICE")
public interface AuthFeignClient {

    @PostMapping("/create-user")
    String createUser(@RequestParam String email,
                      @RequestParam String groupName);

}
