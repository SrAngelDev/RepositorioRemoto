package srangeldev.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    static {
        try(InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if(input == null) {
                throw new RuntimeException("No se encontró el archivo config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando configuración", e);
        }
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
