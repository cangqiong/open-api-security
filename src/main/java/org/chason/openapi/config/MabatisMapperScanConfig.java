package org.chason.openapi.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * Mybatis扫描配置文件
 *
 * @author chason
 * @date 2017-03-10
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)
public class MabatisMapperScanConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("org.chason.openapi.mapper");
        return mapperScannerConfigurer;
    }
}
