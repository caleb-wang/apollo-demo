package com.it;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wanglb@belink.com
 * @version V1.0
 * @title
 * @description
 * @date 2024-06-20 13:49
 */
@SpringBootApplication
@EnableApolloConfig
public class ApolloConfigCenterClient {

    public static void main(String[] args) {
        SpringApplication.run(ApolloConfigCenterClient.class, args);
    }
}
