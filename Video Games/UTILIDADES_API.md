# Utilidades de la API - Documentación

## Descripción
Este documento describe las utilidades creadas para centralizar la configuración y estandarizar las respuestas de la API.

## Archivos de Utilidades

### 1. ApiConstants.java
**Ubicación:** `src/main/java/com/ncapas/actividadl3capasv/util/ApiConstants.java`

**Propósito:** Centraliza todas las constantes de la API como rutas, URLs, mensajes y configuraciones.

#### Características:
- ✅ URLs base para diferentes entornos
- ✅ Rutas de endpoints organizadas por recurso
- ✅ Mensajes de respuesta estandarizados
- ✅ Constantes de validación
- ✅ Métodos helper para construir URLs

#### Uso:
```java
// Obtener URL base
String baseUrl = ApiConstants.BASE_URL;

// Construir URL para obtener usuario por ID
String userUrl = ApiConstants.buildUserByIdUrl("user-id-here");

// Usar constantes de roles
String role = ApiConstants.ROLE_ADMIN;
```

### 2. EnvironmentConfig.java
**Ubicación:** `src/main/java/com/ncapas/actividadl3capasv/util/EnvironmentConfig.java`

**Propósito:** Maneja la configuración según el entorno de ejecución (dev, staging, prod).

#### Características:
- ✅ Detección automática del entorno
- ✅ URLs base según el perfil activo
- ✅ Configuración dinámica del puerto
- ✅ Métodos para verificar el entorno actual

#### Uso:
```java
@Autowired
private EnvironmentConfig envConfig;

// Obtener URL base según entorno
String baseUrl = envConfig.getBaseUrl();

// Verificar si estamos en desarrollo
if (envConfig.isDevelopment()) {
    // Lógica específica para desarrollo
}

// Construir URL completa
String apiUrl = envConfig.buildApiUrl("/api/users");
```

### 3. ApiResponse.java
**Ubicación:** `src/main/java/com/ncapas/actividadl3capasv/util/ApiResponse.java`

**Propósito:** Estandariza el formato de las respuestas de la API.

#### Características:
- ✅ Respuestas tipadas genéricas
- ✅ Timestamp automático
- ✅ Métodos estáticos para crear respuestas
- ✅ Soporte para mensajes de éxito y error

#### Uso:
```java
// Respuesta exitosa con datos
ApiResponse<User> response = ApiResponse.success("Usuario creado", user);

// Respuesta exitosa sin datos
ApiResponse<Void> response = ApiResponse.success("Usuario eliminado");

// Respuesta de error
ApiResponse<Void> errorResponse = ApiResponse.error("Usuario no encontrado");

// Agregar ruta de la petición
response.withPath("/api/users/123");
```

## Ejemplo de Respuesta Estandarizada

### Respuesta Exitosa:
```json
{
    "success": true,
    "message": "Usuario creado exitosamente",
    "data": {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "username": "usuario1",
        "roles": "USER"
    },
    "timestamp": "2024-01-15T10:30:45.123",
    "path": "/api/users"
}
```

### Respuesta de Error:
```json
{
    "success": false,
    "message": "El nombre de usuario ya existe",
    "data": null,
    "timestamp": "2024-01-15T10:30:45.123",
    "path": "/api/users"
}
```

## Configuración de Entornos

### Desarrollo (dev)
- **URL Base:** `http://localhost:8081`
- **Puerto:** 8081
- **Logging:** Detallado

### Staging
- **URL Base:** `https://staging.tu-dominio.com`
- **Puerto:** Configurable
- **Logging:** Moderado

### Producción (prod)
- **URL Base:** `https://tu-dominio-produccion.com`
- **Puerto:** Configurable
- **Logging:** Mínimo

## Constantes Disponibles

### Rutas de API:
- `API_BASE_PATH` = "/api"
- `USERS_BASE_PATH` = "/api/users"
- `VIDEOGAMES_BASE_PATH` = "/api/videogames"

### Endpoints de Users:
- `USERS_CREATE` = "/api/users"
- `USERS_GET_ALL` = "/api/users"
- `USERS_GET_BY_ID` = "/api/users/{id}"
- `USERS_UPDATE` = "/api/users/{id}"
- `USERS_DELETE` = "/api/users/{id}"
- `USERS_GET_BY_USERNAME` = "/api/users/username/{username}"

### Roles de Usuario:
- `ROLE_USER` = "USER"
- `ROLE_ADMIN` = "ADMIN"
- `ROLE_MODERATOR` = "MODERATOR"

### Validaciones:
- `MIN_USERNAME_LENGTH` = 3
- `MAX_USERNAME_LENGTH` = 50
- `MIN_PASSWORD_LENGTH` = 6
- `MAX_PASSWORD_LENGTH` = 100

## Integración con Controladores

Para usar estas utilidades en los controladores:

```java
@RestController
@RequestMapping(ApiConstants.USERS_BASE_PATH)
public class UsersController {
    
    @Autowired
    private EnvironmentConfig envConfig;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Users>> createUser(@RequestBody Users user) {
        try {
            Users createdUser = usersService.createUser(user);
            ApiResponse<Users> response = ApiResponse.success(
                ApiConstants.USER_CREATED_SUCCESS, 
                createdUser
            ).withPath(ApiConstants.USERS_CREATE);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            ApiResponse<Users> errorResponse = ApiResponse.error(e.getMessage())
                .withPath(ApiConstants.USERS_CREATE);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
```

## Beneficios

1. **Mantenibilidad:** Cambios centralizados en un solo lugar
2. **Consistencia:** Respuestas estandarizadas en toda la API
3. **Flexibilidad:** Configuración automática según el entorno
4. **Reutilización:** Constantes y utilidades reutilizables
5. **Documentación:** Código autodocumentado con constantes descriptivas 