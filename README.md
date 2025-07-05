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
