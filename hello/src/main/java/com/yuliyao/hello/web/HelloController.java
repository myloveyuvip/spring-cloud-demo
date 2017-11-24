package com.yuliyao.hello.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YuLiyao
 * @date 2017/11/23
 */
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CounterService counterService;
    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String hello() {
        //自定义访问次数统计，可访问/metrics查看
        counterService.increment("didispace.hello.count");

        ServiceInstance serviceInstance = client.getLocalServiceInstance();
        logger.info("/hello,host:{},service_id:{}", serviceInstance.getHost(), serviceInstance.getPort());

        return "Hello World!";
    }
}
