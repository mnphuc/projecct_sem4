package com.project.sem4.model;

public class PermissionDetail {
//    DetailId int primary key identity(1,1),
//    PermissionId int,
//    ActionName nvarchar(255),
//    ActionCode nvarchar(255),
//    CheckAction nvarchar(255)
    int Id;
    int PermissionId;
    String ActionName;
    String ActionCode;
    String CheckAction;

    public PermissionDetail() {
    }

    public PermissionDetail(int id, int permissionId, String actionName, String actionCode, String checkAction) {
        Id = id;
        PermissionId = permissionId;
        ActionName = actionName;
        ActionCode = actionCode;
        CheckAction = checkAction;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(int permissionId) {
        PermissionId = permissionId;
    }

    public String getActionName() {
        return ActionName;
    }

    public void setActionName(String actionName) {
        ActionName = actionName;
    }

    public String getActionCode() {
        return ActionCode;
    }

    public void setActionCode(String actionCode) {
        ActionCode = actionCode;
    }

    public String getCheckAction() {
        return CheckAction;
    }

    public void setCheckAction(String checkAction) {
        CheckAction = checkAction;
    }
}
