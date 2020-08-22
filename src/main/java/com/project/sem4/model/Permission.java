package com.project.sem4.model;

public class Permission {
//    PermisionId int primary key identity(1,1),
//    PermisionName nvarchar(255) not null
    int Id;
    String PermissionName;

    public Permission() {
    }

    public Permission(int id, String permissionName) {
        Id = id;
        PermissionName = permissionName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPermissionName() {
        return PermissionName;
    }

    public void setPermissionName(String permissionName) {
        PermissionName = permissionName;
    }
}
