# API de Usuarios - Documentación

## Descripción
Esta API proporciona funcionalidades CRUD completas para la gestión de usuarios en el sistema.

## Endpoints Disponibles

### 1. Crear Usuario
**POST** `/api/users`

**Body:**
```json
{
    "username": "usuario1",
    "password": "password123",
    "roles": "USER"
}
```

**Respuesta exitosa (201):**
```json
{
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "username": "usuario1",
    "password": "password123",
    "roles": "USER"
}
```

### 2. Obtener Usuario por ID
**GET** `/api/users/{id}`

**Respuesta exitosa (200):**
```json
{
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "username": "usuario1",
    "password": "password123",
    "roles": "USER"
}
```

### 3. Obtener Todos los Usuarios
**GET** `/api/users`

**Respuesta exitosa (200):**
```json
[
    {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "username": "usuario1",
        "password": "password123",
        "roles": "USER"
    },
    {
        "id": "550e8400-e29b-41d4-a716-446655440001",
        "username": "admin",
        "password": "admin123",
        "roles": "ADMIN"
    }
]
```

### 4. Actualizar Usuario
**PUT** `/api/users/{id}`

**Body:**
```json
{
    "username": "usuario1_actualizado",
    "password": "nueva_password",
    "roles": "ADMIN"
}
```

**Respuesta exitosa (200):**
```json
{
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "username": "usuario1_actualizado",
    "password": "nueva_password",
    "roles": "ADMIN"
}
```

### 5. Eliminar Usuario
**DELETE** `/api/users/{id}`

**Respuesta exitosa (204):** Sin contenido

### 6. Buscar Usuario por Username
**GET** `/api/users/username/{username}`

**Respuesta exitosa (200):**
```json
{
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "username": "usuario1",
    "password": "password123",
    "roles": "USER"
}
```

## Códigos de Estado HTTP

- **200 OK**: Operación exitosa
- **201 Created**: Recurso creado exitosamente
- **204 No Content**: Recurso eliminado exitosamente
- **400 Bad Request**: Datos de entrada inválidos
- **404 Not Found**: Recurso no encontrado

## Validaciones

- El `username` debe ser único en el sistema
- Todos los campos son obligatorios al crear un usuario
- Al actualizar, solo se modifican los campos proporcionados

## Ejemplos de Uso con cURL

### Crear usuario:
```bash
curl -X POST http://localhost:8081/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "nuevo_usuario",
    "password": "mi_password",
    "roles": "USER"
  }'
```

### Obtener todos los usuarios:
```bash
curl -X GET http://localhost:8081/api/users
```

### Actualizar usuario:
```bash
curl -X PUT http://localhost:8081/api/users/550e8400-e29b-41d4-a716-446655440000 \
  -H "Content-Type: application/json" \
  -d '{
    "roles": "ADMIN"
  }'
```

### Eliminar usuario:
```bash
curl -X DELETE http://localhost:8081/api/users/550e8400-e29b-41d4-a716-446655440000
```

## Estructura del Proyecto

```
src/main/java/com/ncapas/actividadl3capasv/
├── Domain/
│   ├── Entities/
│   │   └── Users.java
│   └── DTOs/
│       ├── UserDTO.java
│       └── CreateUserDTO.java
├── repository/
│   └── iUsersRepository.java
├── service/
│   ├── iUsersService.java
│   └── impl/
│       └── UsersServiceImpl.java
└── controller/
    └── UsersController.java
```

## Notas de Seguridad

- En una implementación real, las contraseñas deberían ser hasheadas
- Se recomienda implementar autenticación y autorización
- Los DTOs ayudan a separar la lógica de presentación de la entidad
- Considerar implementar validaciones más robustas con Bean Validation 