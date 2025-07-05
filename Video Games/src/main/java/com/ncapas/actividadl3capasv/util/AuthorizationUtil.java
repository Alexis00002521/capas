package com.ncapas.actividadl3capasv.util;

import com.ncapas.actividadl3capasv.Domain.Entities.Admin;
import com.ncapas.actividadl3capasv.Domain.Entities.Users;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Utilidad para manejar autorización basada en roles y permisos
 */
@Component
public class AuthorizationUtil {

    /**
     * Verifica si un usuario tiene un permiso específico
     */
    public boolean hasPermission(Users user, String permission) {
        if (user == null) return false;
        
        // Si es super admin, tiene todos los permisos
        if (isSuperAdmin(user)) {
            return true;
        }
        
        // Usuarios regulares solo tienen permisos de lectura
        return isReadPermission(permission);
    }

    /**
     * Verifica si un administrador tiene un permiso específico
     */
    public boolean hasPermission(Admin admin, String permission) {
        if (admin == null) return false;
        
        // Si es super admin, tiene todos los permisos
        if (admin.isSuperAdmin()) {
            return true;
        }
        
        // Verificar permisos específicos del admin
        return admin.getPermissions() != null && 
               admin.getPermissions().contains(permission);
    }

    /**
     * Verifica si un usuario es super administrador
     */
    public boolean isSuperAdmin(Users user) {
        if (user == null) return false;
        return ApiConstants.ROLE_SUPER_ADMIN.equals(user.getRoles());
    }

    /**
     * Verifica si un usuario es administrador
     */
    public boolean isAdmin(Users user) {
        if (user == null) return false;
        return ApiConstants.ROLE_SUPER_ADMIN.equals(user.getRoles());
    }

    /**
     * Verifica si un permiso es de solo lectura
     */
    public boolean isReadPermission(String permission) {
        return ApiConstants.PERMISSION_USER_READ.equals(permission) ||
               ApiConstants.PERMISSION_VIDEOGAME_READ.equals(permission);
    }

    /**
     * Verifica si un permiso es de escritura (crear, actualizar, eliminar)
     */
    public boolean isWritePermission(String permission) {
        return permission.contains("CREATE") ||
               permission.contains("UPDATE") ||
               permission.contains("DELETE");
    }

    /**
     * Obtiene los permisos por defecto para un super admin
     */
    public String getSuperAdminPermissions() {
        return String.join(",", Arrays.asList(
            ApiConstants.PERMISSION_USER_CREATE,
            ApiConstants.PERMISSION_USER_READ,
            ApiConstants.PERMISSION_USER_UPDATE,
            ApiConstants.PERMISSION_USER_DELETE,
            ApiConstants.PERMISSION_VIDEOGAME_CREATE,
            ApiConstants.PERMISSION_VIDEOGAME_READ,
            ApiConstants.PERMISSION_VIDEOGAME_UPDATE,
            ApiConstants.PERMISSION_VIDEOGAME_DELETE,
            ApiConstants.PERMISSION_ADMIN_MANAGEMENT
        ));
    }

    /**
     * Obtiene los permisos por defecto para un usuario regular
     */
    public String getUserPermissions() {
        return String.join(",", Arrays.asList(
            ApiConstants.PERMISSION_USER_READ,
            ApiConstants.PERMISSION_VIDEOGAME_READ
        ));
    }

    /**
     * Verifica si un usuario puede crear otros usuarios
     */
    public boolean canCreateUsers(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_USER_CREATE);
    }

    /**
     * Verifica si un usuario puede leer otros usuarios
     */
    public boolean canReadUsers(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_USER_READ);
    }

    /**
     * Verifica si un usuario puede actualizar otros usuarios
     */
    public boolean canUpdateUsers(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_USER_UPDATE);
    }

    /**
     * Verifica si un usuario puede eliminar otros usuarios
     */
    public boolean canDeleteUsers(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_USER_DELETE);
    }

    /**
     * Verifica si un usuario puede crear videojuegos
     */
    public boolean canCreateVideoGames(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_VIDEOGAME_CREATE);
    }

    /**
     * Verifica si un usuario puede leer videojuegos
     */
    public boolean canReadVideoGames(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_VIDEOGAME_READ);
    }

    /**
     * Verifica si un usuario puede actualizar videojuegos
     */
    public boolean canUpdateVideoGames(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_VIDEOGAME_UPDATE);
    }

    /**
     * Verifica si un usuario puede eliminar videojuegos
     */
    public boolean canDeleteVideoGames(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_VIDEOGAME_DELETE);
    }

    /**
     * Verifica si un usuario puede gestionar administradores
     */
    public boolean canManageAdmins(Users user) {
        return hasPermission(user, ApiConstants.PERMISSION_ADMIN_MANAGEMENT);
    }

    // Métodos sobrecargados para Admin
    public boolean canCreateUsers(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_USER_CREATE);
    }

    public boolean canReadUsers(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_USER_READ);
    }

    public boolean canUpdateUsers(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_USER_UPDATE);
    }

    public boolean canDeleteUsers(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_USER_DELETE);
    }

    public boolean canCreateVideoGames(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_VIDEOGAME_CREATE);
    }

    public boolean canReadVideoGames(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_VIDEOGAME_READ);
    }

    public boolean canUpdateVideoGames(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_VIDEOGAME_UPDATE);
    }

    public boolean canDeleteVideoGames(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_VIDEOGAME_DELETE);
    }

    public boolean canManageAdmins(Admin admin) {
        return hasPermission(admin, ApiConstants.PERMISSION_ADMIN_MANAGEMENT);
    }
} 