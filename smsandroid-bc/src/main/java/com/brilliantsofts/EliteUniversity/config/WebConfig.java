package com.brilliantsofts.EliteUniversity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir:./uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absoluteDir = java.nio.file.Paths.get(uploadDir).toAbsolutePath().toString();
        if (!absoluteDir.endsWith("/")) {
            absoluteDir += "/";
        }
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + absoluteDir);
    }
}
