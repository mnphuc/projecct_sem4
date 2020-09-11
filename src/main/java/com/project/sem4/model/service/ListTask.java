package com.project.sem4.model.service;

import com.project.sem4.model.map.OrderDetailMap;
import com.project.sem4.model.view.OrderView;
import lombok.Data;

import java.util.List;

@Data
public class ListTask {
    private Integer checkTask;
    private String email;
    private String object;
    private String message;
}
