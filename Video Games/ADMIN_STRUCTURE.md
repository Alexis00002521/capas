# Estructura de Administradores - Documentación Completa

## Descripción
Este documento describe la implementación completa del sistema de administradores con roles, permisos y funcionalidades específicas.

## Arquitectura del Sistema

### 🏗️ **Entidades Principales:**

#### 1. **Users** (Entidad Base)
- **Propósito:** Entidad base para todos los usuarios del sistema
- **Campos:** id, username, password, roles
- **Herencia:** Admin extiende de Users

#### 2. **Admin** (Entidad Específica)
- **Propósito:** Administradores con funcionalidades extendidas
- **Campos adicionales:**
  - `permissions`: Permisos específicos (JSON)
  - `lastLogin`: Último acceso
  - `isSuperAdmin`: Indica si es super administrador
  - `createdBy`: Quién creó el admin
  - `createdAt/updatedAt`: Timestamps

#### 3. **Role** (Entidad de Roles)
- **Propósito:** Definir roles del sistema
- **Campos:** id, name, description, isActive, priority

#### 4. **AdminPermission** (Entidad de Permisos)
- **Propósito:** Permisos específicos para administradores
- **Campos:** id, permissionName, description, resource, action, isActive, priority

## Estructura de Archivos

```
src/main/java/com/ncapas/actividadl3capasv/
├── Domain/
│   ├── Entities/
│   │   ├── Users.java (base)
│   │   ├── Admin.java (extiende Users)
│   │   ├── Role.java
│   │   └── AdminPermission.java
│   └── DTOs/
│       ├── AdminDTO.java
│       └── CreateAdminDTO.java
├── repository/
│   ├── iUsersRepository.java
│   ├── iAdminRepository.java
│   └── iRoleRepository.java
├── service/
│   ├── iUsersService.java
│   ├── impl/UsersServiceImpl.java
│   ├── iAdminService.java
│   └── impl/AdminServiceImpl.java
├── controller/
│   ├── UsersController.java
│   └── AdminController.java
└── util/
    ├── ApiConstants.java
    ├── ApiResponse.java
    ├── EnvironmentConfig.java
    └── DataInitializer.java
```

## Funcionalidades Implementadas

### 🔐 **Autenticación y Autorización:**
- ✅ Login de administradores
- ✅ Validación de credenciales
- ✅ Verificación de permisos
- ✅ Actualización de último acceso

### 👥 **Gestión de Administradores:**
- ✅ Crear administradores
- ✅ Listar todos los administradores
- ✅ Buscar por ID, username
- ✅ Actualizar información
- ✅ Eliminar administradores
- ✅ Obtener super administradores
- ✅ Obtener administradores activos

### 🎯 **Gestión de Roles y Permisos:**
- ✅ Promover a super administrador
- ✅ Degradar de super administrador
- ✅ Actualizar permisos
- ✅ Verificar permisos específicos

## Endpoints de la API

### **Base URL:** `http://localhost:8081/api/admins`

#### 🔧 **Operaciones CRUD:**
- **POST** `/api/admins` - Crear administrador
- **GET** `/api/admins` - Obtener todos los administradores
- **GET** `/api/admins/{id}` - Obtener administrador por ID
- **PUT** `/api/admins/{id}` - Actualizar administrador
- **DELETE** `/api/admins/{id}` - Eliminar administrador

#### 🔍 **Búsquedas Específicas:**
- **GET** `/api/admins/username/{username}` - Buscar por username
- **GET** `/api/admins/super` - Obtener super administradores
- **GET** `/api/admins/active` - Obtener administradores activos

#### 🎛️ **Gestión de Permisos:**
- **POST** `/api/admins/{id}/promote` - Promover a super admin
- **POST** `/api/admins/{id}/demote` - Degradar de super admin
- **PUT** `/api/admins/{id}/permissions` - Actualizar permisos

#### 🔐 **Autenticación:**
- **POST** `/api/admins/login` - Login de administrador

## Ejemplos de Uso

### 1. Crear un Administrador
```bash
curl -X POST http://localhost:8081/api/admins \
  -H "Content-Type: application/json" \
  -d '{
    "username": "nuevo_admin",
    "password": "password123",
    "permissions": "USER_READ,VIDEOGAME_READ",
    "isSuperAdmin": false,
    "createdBy": "superadmin"
  }'
```

### 2. Login de Administrador
```bash
curl -X POST http://localhost:8081/api/admins/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

### 3. Promover a Super Administrador
```bash
curl -X POST http://localhost:8081/api/admins/550e8400-e29b-41d4-a716-446655440000/promote
```

### 4. Obtener Super Administradores
```bash
curl -X GET http://localhost:8081/api/admins/super
```

## Roles y Permisos

### 🎭 **Roles por Defecto:**
1. **SUPER_ADMIN** (Prioridad: 1)
   - Todos los permisos del sistema
   - Puede crear otros super administradores
   - Acceso completo a todas las funcionalidades
   - Gestión de usuarios y videojuegos

2. **USER** (Prioridad: 2)
   - Usuario regular del sistema
   - Permisos básicos de lectura
   - Acceso limitado a funcionalidades

### 🔑 **Permisos Disponibles:**
- `USER_MANAGEMENT` - Gestión completa de usuarios
- `USER_READ` - Solo lectura de usuarios
- `VIDEOGAME_MANAGEMENT` - Gestión completa de videojuegos
- `VIDEOGAME_READ` - Solo lectura de videojuegos
- `ADMIN_MANAGEMENT` - Gestión de administradores
- `SYSTEM_CONFIG` - Configuración del sistema
- `ALL_PERMISSIONS` - Todos los permisos

## Inicialización Automática

### 🚀 **Al Iniciar la Aplicación:**
El `DataInitializer` crea automáticamente:

1. **Roles por defecto** (si no existen):
   - `SUPER_ADMIN` - Super Administrador con todos los permisos
   - `USER` - Usuario regular del sistema

2. **Super administrador inicial:**
   - Username: `superadmin`
   - Password: `admin123`
   - Permisos: `ALL_PERMISSIONS`

3. **Administrador de ejemplo:**
   - Username: `admin`
   - Password: `admin123`
   - Permisos: `USER_MANAGEMENT,VIDEOGAME_MANAGEMENT`

## Respuestas Estandarizadas

### ✅ **Respuesta Exitosa:**
```json
{
    "success": true,
    "message": "Administrador creado exitosamente",
    "data": {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "username": "nuevo_admin",
        "permissions": "USER_READ,VIDEOGAME_READ",
        "isSuperAdmin": false,
        "createdBy": "superadmin",
        "createdAt": "2024-01-15T10:30:45.123",
        "updatedAt": "2024-01-15T10:30:45.123"
    },
    "timestamp": "2024-01-15T10:30:45.123",
    "path": "/api/admins"
}
```

### ❌ **Respuesta de Error:**
```json
{
    "success": false,
    "message": "El nombre de usuario ya existe",
    "data": null,
    "timestamp": "2024-01-15T10:30:45.123",
    "path": "/api/admins"
}
```

## Seguridad y Mejores Prácticas

### 🔒 **Consideraciones de Seguridad:**
1. **Contraseñas:** En producción, implementar hashing (BCrypt)
2. **JWT:** Implementar tokens JWT para autenticación
3. **Validación:** Agregar validaciones más robustas
4. **Auditoría:** Log de todas las acciones de administradores
5. **Rate Limiting:** Limitar intentos de login

### 🛡️ **Validaciones Implementadas:**
- ✅ Username único
- ✅ AdminCode único
- ✅ Verificación de existencia antes de operaciones
- ✅ Validación de credenciales en login

### 🔄 **Próximas Mejoras:**
1. Implementar Spring Security
2. Agregar encriptación de contraseñas
3. Implementar JWT tokens
4. Agregar validaciones con Bean Validation
5. Implementar auditoría de acciones
6. Agregar paginación en listados

## Uso en el Frontend

### 📱 **Ejemplo de Integración:**
```javascript
// Login de administrador
const loginAdmin = async (username, password) => {
    const response = await fetch('/api/admins/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    });
    
    const result = await response.json();
    if (result.success) {
        // Guardar token y datos del admin
        localStorage.setItem('adminToken', result.data.token);
        localStorage.setItem('adminData', JSON.stringify(result.data));
    }
    return result;
};

// Verificar permisos
const hasPermission = (permission) => {
    const adminData = JSON.parse(localStorage.getItem('adminData'));
    return adminData.permissions.includes(permission);
};
```

Esta estructura proporciona un sistema completo y escalable para la gestión de administradores con roles y permisos específicos. 