package com.electronicstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ✅ Allow your frontend origin (Vite default)
        config.setAllowedOriginPatterns(List.of("http://localhost:5173", "https://electronic-store-frontend-1nz111fuk-bharanidharan-gs-projects.vercel.app"));

        // ✅ Allow standard HTTP methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // ✅ Allow all headers
        config.setAllowedHeaders(List.of("*"));

        // ✅ Allow sending cookies, authorization headers, etc.
        config.setAllowCredentials(true);

        // ✅ Register this configuration for all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
