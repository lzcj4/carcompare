package com.carcompare.listener;

import com.carcompare.shiro.StatelessDefaultSubjectFactory;
import com.carcompare.shiro.jwt.JwtRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringEventListener {

    @Bean(name = "jwtRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public JwtRealm jwtRealm() {
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setCachingEnabled(true);
        return jwtRealm;
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 替换默认的DefaultSubjectFactory，用于关闭session功能
        securityManager.setSubjectFactory(subjectFactory());
        securityManager.setSessionManager(sessionManager());

        // 关闭session存储，禁用Session作为存储策略的实现，
        // 但它没有完全地禁用Session所以需要配合SubjectFactory中的context.setSessionCreationEnabled(false)
        ((DefaultSessionStorageEvaluator) ((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);

        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    /**
     * Subject工厂管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory() {
        DefaultWebSubjectFactory subjectFactory = new StatelessDefaultSubjectFactory();
        return subjectFactory;
    }

    /**
     * 会话管理器
     *
     * @return
     */
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        // 关闭session定时检查，通过setSessionValidationSchedulerEnabled禁用掉会话调度器
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        DefaultWebSecurityManager manager = (DefaultWebSecurityManager)context.getBean("securityManager");
        AuthorizingRealm realm = (AuthorizingRealm) context.getBean("jwtRealm");
        manager.setRealm(realm);
    }
}
