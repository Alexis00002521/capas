package com.ncapas.actividadl3capasv.util;

/**
 * Constantes de la API para centralizar rutas y URLs
 */
public class ApiConstants {
    
    // URL Base de la aplicación
    public static final String BASE_URL = "http://localhost:8081";
    
    // Rutas base de los controladores
    public static final String API_BASE_PATH = "/api";
    public static final String USERS_BASE_PATH = API_BASE_PATH + "/users";
    public static final String VIDEOGAMES_BASE_PATH = API_BASE_PATH + "/videogames";
    public static final String ADMIN_BASE_PATH = API_BASE_PATH + "/admins";
    
    // Endpoints específicos para Users
    public static final String USERS_CREATE = USERS_BASE_PATH;
    public static final String USERS_GET_ALL = USERS_BASE_PATH;
    public static final String USERS_GET_BY_ID = USERS_BASE_PATH + "/{id}";
    public static final String USERS_UPDATE = USERS_BASE_PATH + "/{id}";
    public static final String USERS_DELETE = USERS_BASE_PATH + "/{id}";
    public static final String USERS_GET_BY_USERNAME = USERS_BASE_PATH + "/username/{username}";
    
    // Endpoints específicos para VideoGames
    public static final String VIDEOGAMES_CREATE = VIDEOGAMES_BASE_PATH;
    
    // Endpoints específicos para Admins
    public static final String ADMIN_CREATE = ADMIN_BASE_PATH;
    public static final String ADMIN_GET_ALL = ADMIN_BASE_PATH;
    public static final String ADMIN_GET_BY_ID = ADMIN_BASE_PATH + "/{id}";
    public static final String ADMIN_UPDATE = ADMIN_BASE_PATH + "/{id}";
    public static final String ADMIN_DELETE = ADMIN_BASE_PATH + "/{id}";
    public static final String ADMIN_GET_BY_USERNAME = ADMIN_BASE_PATH + "/username/{username}";
    
    // URLs completas para uso en frontend
    public static final String USERS_CREATE_URL = BASE_URL + USERS_CREATE;
    public static final String USERS_GET_ALL_URL = BASE_URL + USERS_GET_ALL;
    public static final String USERS_GET_BY_ID_URL = BASE_URL + USERS_GET_BY_ID;
    public static final String USERS_UPDATE_URL = BASE_URL + USERS_UPDATE;
    public static final String USERS_DELETE_URL = BASE_URL + USERS_DELETE;
    public static final String USERS_GET_BY_USERNAME_URL = BASE_URL + USERS_GET_BY_USERNAME;
    
    public static final String VIDEOGAMES_CREATE_URL = BASE_URL + VIDEOGAMES_CREATE;
    
    // Headers comunes
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String ACCEPT_JSON = "application/json";
    
    // Mensajes de respuesta
    public static final String USER_CREATED_SUCCESS = "Usuario creado exitosamente";
    public static final String USER_UPDATED_SUCCESS = "Usuario actualizado exitosamente";
    public static final String USER_DELETED_SUCCESS = "Usuario eliminado exitosamente";
    public static final String USER_NOT_FOUND = "Usuario no encontrado";
    public static final String USERNAME_ALREADY_EXISTS = "El nombre de usuario ya existe";
    
    // Roles de usuario
    public static final String ROLE_USER = "USER";
    public static final String ROLE_SUPER_ADMIN = "SUPER_ADMIN";
    
    // Permisos específicos
    public static final String PERMISSION_USER_CREATE = "USER_CREATE";
    public static final String PERMISSION_USER_READ = "USER_READ";
    public static final String PERMISSION_USER_UPDATE = "USER_UPDATE";
    public static final String PERMISSION_USER_DELETE = "USER_DELETE";
    public static final String PERMISSION_VIDEOGAME_CREATE = "VIDEOGAME_CREATE";
    public static final String PERMISSION_VIDEOGAME_READ = "VIDEOGAME_READ";
    public static final String PERMISSION_VIDEOGAME_UPDATE = "VIDEOGAME_UPDATE";
    public static final String PERMISSION_VIDEOGAME_DELETE = "VIDEOGAME_DELETE";
    public static final String PERMISSION_ADMIN_MANAGEMENT = "ADMIN_MANAGEMENT";
    
    // Configuración de paginación (para futuras implementaciones)
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;
    
    // Configuración de validación
    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int MAX_USERNAME_LENGTH = 50;
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 100;
    
    /**
     * Construye la URL completa para obtener un usuario por ID
     * @param id ID del usuario
     * @return URL completa
     */
    public static String buildUserByIdUrl(String id) {
        return BASE_URL + USERS_GET_BY_ID.replace("{id}", id);
    }
    
    /**
     * Construye la URL completa para actualizar un usuario
     * @param id ID del usuario
     * @return URL completa
     */
    public static String buildUserUpdateUrl(String id) {
        return BASE_URL + USERS_UPDATE.replace("{id}", id);
    }
    
    /**
     * Construye la URL completa para eliminar un usuario
     * @param id ID del usuario
     * @return URL completa
     */
    public static String buildUserDeleteUrl(String id) {
        return BASE_URL + USERS_DELETE.replace("{id}", id);
    }
    
    /**
     * Construye la URL completa para buscar un usuario por username
     * @param username Nombre de usuario
     * @return URL completa
     */
    public static String buildUserByUsernameUrl(String username) {
        return BASE_URL + USERS_GET_BY_USERNAME.replace("{username}", username);
    }
} 