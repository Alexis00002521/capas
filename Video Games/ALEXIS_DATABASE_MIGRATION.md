# Migración de Alexis a Base de Datos

## Resumen de Cambios

Se ha migrado el usuario "Alexis" desde el sistema de autenticación en memoria (`InMemoryUserDetailsService`) hacia la base de datos, permitiendo que sea gestionable como los demás administradores.

## Cambios Realizados

### 1. Eliminación del InMemoryUserDetailsService
- **Archivo eliminado**: `src/main/java/com/ncapas/actividadl3capasv/service/InMemoryUserDetailsService.java`
- **Razón**: Ya no es necesario tener usuarios hardcodeados en memoria

### 2. Actualización del CompositeUserDetailsService
- **Archivo modificado**: `src/main/java/com/ncapas/actividadl3capasv/service/CompositeUserDetailsService.java`
- **Cambios**:
  - Eliminada la dependencia de `InMemoryUserDetailsService`
  - Ahora solo consulta la base de datos
  - Lanza `UsernameNotFoundException` si el usuario no existe en la BD

### 3. Actualización del DataInitializer
- **Archivo modificado**: `src/main/java/com/ncapas/actividadl3capasv/util/DataInitializer.java`
- **Cambios**:
  - Agregado `PasswordEncoder` para hashear contraseñas
  - Creado Alexis como super administrador en la base de datos
  - Contraseña: `Ale123456` (hasheada con BCrypt)
  - Permisos: Todos los permisos de super administrador

### 4. Actualización del AdminServiceImpl
- **Archivo modificado**: `src/main/java/com/ncapas/actividadl3capasv/service/impl/AdminServiceImpl.java`
- **Cambios**:
  - Agregado `PasswordEncoder` para hashear contraseñas al crear/actualizar
  - Actualizada validación de credenciales para usar `passwordEncoder.matches()`

## Usuarios por Defecto

Al iniciar la aplicación, se crean automáticamente los siguientes administradores:

1. **superadmin** / admin123 (Super Admin)
2. **admin** / admin123 (Admin regular)
3. **Alexis** / Ale123456 (Super Admin) ← **NUEVO**

## Endpoints Disponibles para Gestión

### Obtener todos los administradores
```
GET /api/admins
```

### Obtener Alexis específicamente
```
GET /api/admins/username/Alexis
```

### Actualizar Alexis
```
PUT /api/admins/{id}
```

### Eliminar Alexis (no recomendado)
```
DELETE /api/admins/{id}
```

### Promover/Degradar Alexis
```
POST /api/admins/{id}/promote    # Hacer super admin
POST /api/admins/{id}/demote     # Quitar super admin
```

### Actualizar permisos de Alexis
```
PUT /api/admins/{id}/permissions
```

## Autenticación

Alexis ahora puede autenticarse usando el endpoint estándar:

```
POST /api/auth/login
Content-Type: application/json

{
  "username": "Alexis",
  "password": "Ale123456"
}
```

## Ventajas de la Migración

1. **Gestión completa**: Alexis puede ser creado, actualizado, eliminado y gestionado como cualquier otro administrador
2. **Seguridad mejorada**: Las contraseñas se almacenan hasheadas con BCrypt
3. **Consistencia**: Todos los administradores están en el mismo lugar (base de datos)
4. **Auditoría**: Se registra quién creó el admin y cuándo
5. **Flexibilidad**: Se pueden asignar permisos específicos y roles

## Notas Importantes

- Las contraseñas se hashean automáticamente al crear/actualizar administradores
- Alexis tiene todos los permisos de super administrador por defecto
- El sistema mantiene compatibilidad con la autenticación JWT existente
- No se requiere reinicio de la aplicación para aplicar cambios en administradores 