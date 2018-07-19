package com.carcompare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.carcompare"})
@MapperScan("com.carcompare.mapper")
@ImportResource(locations = {"classpath:kaptcha-config.xml"})
public class Entry extends SpringBootServletInitializer {
    public static  void  main(String[] args) throws Exception{
        SpringApplication.run(Entry.class,args);
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return  new AppErrorPageRegistrar();
    }

    private static  class  AppErrorPageRegistrar implements ErrorPageRegistrar{
        @Override
        public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
            errorPageRegistry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/index.html"));
        }
    }
}
