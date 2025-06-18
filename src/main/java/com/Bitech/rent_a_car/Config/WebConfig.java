package com.Bitech.rent_a_car.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/imagens/";

    public WebConfig() {
        System.out.println(">>>> WebConfig carregado! Diretório de imagens: " + UPLOAD_DIR);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(">>>> Registrando handler para servir imagens de: " + UPLOAD_DIR);
        registry.addResourceHandler("/imagens/**")
                .addResourceLocations("file:" + UPLOAD_DIR);
    }
}