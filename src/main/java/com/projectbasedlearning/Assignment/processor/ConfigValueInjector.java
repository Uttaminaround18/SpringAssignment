package com.projectbasedlearning.Assignment.processor;

import com.projectbasedlearning.Assignment.annotation.InjectConfigValue;
import com.projectbasedlearning.Assignment.model.ContentTable;
import com.projectbasedlearning.Assignment.repositories.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ConfigValueInjector implements ApplicationRunner {
    @Autowired
    private Environment environment;

    @Autowired
    private AppConfigRepository appConfigRepository;

    @Override
    public void run(ApplicationArguments args) {

        // Iterate over all Spring beans
        String[] beanNames = ApplicationContextProvider.getApplicationContext().getBeanDefinitionNames();

        for (String beanName : beanNames) {
            Object bean = ApplicationContextProvider.getApplicationContext().getBean(beanName);

            for (Field field : bean.getClass().getDeclaredFields()) {
                InjectConfigValue annotation = field.getAnnotation(InjectConfigValue.class);

                if (annotation != null) {
                    String envVarName = annotation.envKey();
                    String envValue = environment.getProperty(envVarName);

                    if (annotation.required() && envValue == null) {
                        throw new IllegalStateException("Missing environment variable: " + envVarName);
                    }

                    ContentTable contentTable = appConfigRepository.findByMyKey(envValue)
                            .orElse(null);
//                                    .orElseThrow(() -> new IllegalStateException("Database entry is missing for the key: " + envValue));
                    final String finalValue;
                    if(contentTable != null)
                         finalValue = contentTable.getMyValue();
                    else
                        finalValue = null;

                    field.setAccessible(true);
                    try {
                        field.set(bean, finalValue);
                        System.out.println("Injected value into " + bean.getClass().getSimpleName() +
                                "." + field.getName() + " = " + finalValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to inject value into field!");
                    }
                }
            }
        }
    }
}
