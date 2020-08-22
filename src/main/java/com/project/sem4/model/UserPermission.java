package com.project.sem4.model;

public class UserPermission {
    int PermissionId;
    int UserId;
    String Licensed;

    public UserPermission() {
    }

    public UserPermission(int permissionId, int userId, String licensed) {
        PermissionId = permissionId;
        UserId = userId;
        Licensed = licensed;
    }

    public int getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(int permissionId) {
        PermissionId = permissionId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getLicensed() {
        return Licensed;
    }

    public void setLicensed(String licensed) {
        Licensed = licensed;
    }
}
