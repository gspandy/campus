package org.campus.core.swagger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

@Configuration
@EnableSwagger
@ComponentScan(basePackages = { "org.campus.controller", "org.campus.domain" })
public class SelfSpringSwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    private final static String SWAGGER_FILTER_CLASS_NAMES = "swagger.properties";

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() throws ClassNotFoundException, IOException {
        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
        swaggerSpringMvcPlugin.apiInfo(apiInfo());
        swaggerSpringMvcPlugin.swaggerGroup("group");
        swaggerSpringMvcPlugin.includePatterns(".*?");
        swaggerSpringMvcPlugin.ignoredParameterTypes(getFilterClasses());
        return swaggerSpringMvcPlugin;
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("My Apps API Title", "My Apps API Description", "My Apps API terms of service",
                "My Apps API Contact Email", "My Apps API Licence Type", "My Apps API License URL");
        return apiInfo;
    }

    private Class<?>[] getFilterClasses() throws IOException, ClassNotFoundException {
        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream(SWAGGER_FILTER_CLASS_NAMES));
        String cls = prop.getProperty("swagger.filter.classes");
        String[] classNames = StringUtils.split(cls, "\\;");
        if (classNames == null || classNames.length == 0) {
            return null;
        }

        List<Class<?>> classes = new ArrayList<Class<?>>(classNames.length);
        int len = 0;
        for (String clazz : classNames) {
            if (ClassUtils.isPresent(clazz, SelfSpringSwaggerConfig.class.getClassLoader())) {
                classes.add(Class.forName(clazz));
                len++;
            }
        }

        Class<?>[] clzs = new Class<?>[len];
        for (int i = 0; i < len; i++) {
            clzs[i] = classes.get(i);
        }

        return clzs;
    }

}
