package com.carcompare.config;

import com.carcompare.license.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(FileRSAKeyCondition.class)
    public IRSAKeyProvider fileRSAKeyProvider(){
        return new FileRSAKeyProvider();
    }

    @Bean
    @Conditional(FixedRSAKeyCondition.class)
    public IRSAKeyProvider fixedRSAKeyProvider(){
        return new FixedRSAKeyProvider();
    }
}
