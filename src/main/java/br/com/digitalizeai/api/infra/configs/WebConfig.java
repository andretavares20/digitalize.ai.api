package br.com.digitalizeai.api.infra.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Certifique-se de que a origem está correta
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type")  // Certifique-se de que "Authorization" está aqui
                .exposedHeaders("Authorization")  // Exponha o cabeçalho "Authorization" para o cliente
                .allowCredentials(true);  // Permite o uso de credenciais, caso necessário
    }
}
