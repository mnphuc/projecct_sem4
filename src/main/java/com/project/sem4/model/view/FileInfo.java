package com.project.sem4.model.view;


import java.text.SimpleDateFormat;
import java.util.Date;

public class FileInfo {
    private String name;

    private Long size;
    private String createTime;
    private String rootDirName;

    private String encName;

    public FileInfo() {
    }

    public FileInfo(String name, Long size, String createTime, String rootDirName, String encName) {
        this.name = name;
        this.size = size;
        this.rootDirName = rootDirName;
        this.encName = encName;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getRootDirName() {
        return rootDirName;
    }

    public void setRootDirName(String rootDirName) {
        this.rootDirName = rootDirName;
    }

    public String getEncName() {
        return encName;
    }

    public void setEncName(String encName) {
        this.encName = encName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "FileInfo [name=" + name + ", size=" + size + ",createTime=" +createTime +", rootDirName=" + rootDirName + ", encName=" + encName
                + "]";
    }
}
