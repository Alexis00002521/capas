package com.ncapas.actividadl3capasv.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuración de entorno para la aplicación
 */
@Component
public class EnvironmentConfig {
    
    @Value("${spring.profiles.active:dev}")
    private String activeProfile;
    
    @Value("${server.port:8081}")
    private String serverPort;
    
    @Value("${spring.application.name:actividad-l3-capas-v}")
    private String applicationName;
    
    /**
     * Obtiene la URL base según el entorno activo
     * @return URL base de la aplicación
     */
    public String getBaseUrl() {
        switch (activeProfile.toLowerCase()) {
            case "prod":
                return "https://tu-dominio-produccion.com";
            case "staging":
                return "https://staging.tu-dominio.com";
            case "dev":
            default:
                return "http://localhost:" + serverPort;
        }
    }
    
    /**
     * Verifica si estamos en entorno de desarrollo
     * @return true si es desarrollo
     */
    public boolean isDevelopment() {
        return "dev".equals(activeProfile.toLowerCase());
    }
    
    /**
     * Verifica si estamos en entorno de producción
     * @return true si es producción
     */
    public boolean isProduction() {
        return "prod".equals(activeProfile.toLowerCase());
    }
    
    /**
     * Obtiene el perfil activo
     * @return perfil activo
     */
    public String getActiveProfile() {
        return activeProfile;
    }
    
    /**
     * Obtiene el puerto del servidor
     * @return puerto del servidor
     */
    public String getServerPort() {
        return serverPort;
    }
    
    /**
     * Obtiene el nombre de la aplicación
     * @return nombre de la aplicación
     */
    public String getApplicationName() {
        return applicationName;
    }
    
    /**
     * Construye la URL completa para un endpoint específico
     * @param endpoint endpoint de la API
     * @return URL completa
     */
    public String buildApiUrl(String endpoint) {
        return getBaseUrl() + endpoint;
    }
} 