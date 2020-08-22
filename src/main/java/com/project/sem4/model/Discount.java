package com.project.sem4.model;

import sun.rmi.runtime.Log;

import java.util.Date;

public class Discount {
//    Id int Primary key identity(1,1),
//    Description nvarchar(255),
//    Code_Discount varchar(255) not null,
//    Type_Discount int,
//    date_end datetime,
//    date_start datetime,
//    select_product bigint null,
//    select_user int null,
//    create_at datetime default getdate()
    int Id;
    String Description;
    String CodeDiscount;
    String TypeDiscount;
    Date DateStart;
    Date DateEnd;
    Long SelectProduct;
    int SelectUser;
    Date CreateAt;

    public Discount() {
    }

    public Discount(int id, String description, String codeDiscount, String typeDiscount, Date dateStart, Date dateEnd, Long selectProduct, int selectUser, Date createAt) {
        Id = id;
        Description = description;
        CodeDiscount = codeDiscount;
        TypeDiscount = typeDiscount;
        DateStart = dateStart;
        DateEnd = dateEnd;
        SelectProduct = selectProduct;
        SelectUser = selectUser;
        CreateAt = createAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCodeDiscount() {
        return CodeDiscount;
    }

    public void setCodeDiscount(String codeDiscount) {
        CodeDiscount = codeDiscount;
    }

    public String getTypeDiscount() {
        return TypeDiscount;
    }

    public void setTypeDiscount(String typeDiscount) {
        TypeDiscount = typeDiscount;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public Long getSelectProduct() {
        return SelectProduct;
    }

    public void setSelectProduct(Long selectProduct) {
        SelectProduct = selectProduct;
    }

    public int getSelectUser() {
        return SelectUser;
    }

    public void setSelectUser(int selectUser) {
        SelectUser = selectUser;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }
}
