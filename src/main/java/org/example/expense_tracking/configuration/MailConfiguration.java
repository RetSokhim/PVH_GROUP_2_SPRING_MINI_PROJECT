package org.example.expense_tracking.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

@Configuration
public class MailConfiguration implements ApplicationContextAware, EnvironmentAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }
    @Override
    public void setEnvironment(Environment environment) {
    }
}
