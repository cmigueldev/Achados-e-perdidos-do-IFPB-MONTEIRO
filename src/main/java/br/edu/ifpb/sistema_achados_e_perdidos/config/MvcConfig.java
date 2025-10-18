package br.edu.ifpb.sistema_achados_e_perdidos.config;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Define o diretório de uploads a partir da raiz do projeto.
        Path uploadDir = Paths.get("uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        // Mapeia a URL '/uploads/**' para o diretório físico onde as imagens são salvas.
        // Agora, o navegador pode acessar as imagens usando um caminho como 'http://localhost:8080/uploads/imagem.jpg'.
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/" + uploadPath + "/");
    }
}
