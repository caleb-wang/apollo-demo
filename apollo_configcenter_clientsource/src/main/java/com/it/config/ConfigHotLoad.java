package com.it.config;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
/**
 * @author wanglb@belink.com
 * @version V1.0
 * @title
 * @description 实现配置热加载
 * @date 2024-06-20 15:07
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
@Slf4j
public class ConfigHotLoad {

    @RefreshScope
    @Bean("dataSource_Bean")
    public DataSource dataSource(DataSourceProperties dataSourceProperties){
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private org.springframework.cloud.context.scope.refresh.RefreshScope refreshScope;

    /** @Author lyy
     * @Description //TODO 监听apollo 的配置变更
     * @Date 16:50 2021/9/15
     * @Param
     * @return 此处填写监听namespace的名称
     **/
    @ApolloConfigChangeListener(value = {ConfigConsts.NAMESPACE_APPLICATION,"dev"},interestedKeyPrefixes = {"spring.datasource"})
    public void onChange(ConfigChangeEvent configChangeEvent){
        // 重新编译DataSource 初始化bean
        refreshScope.refresh("dataSource_Bean");
        log.info("Apollo config changed {}",applicationContext.getBean(DataSourceProperties.class).toString());

    }
}
