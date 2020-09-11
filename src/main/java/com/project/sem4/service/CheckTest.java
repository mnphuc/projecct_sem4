package com.project.sem4.service;

import com.project.sem4.model.service.ListTask;
import com.project.sem4.model.service.Mail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckTest {
    List<ListTask> list = new ArrayList<>();
    public Boolean addTask(ListTask listTask){
        list.add(listTask);
        return true;
    }
    public List<ListTask> getAllTask(){
        return list;
    }
    public Boolean removeTask(ListTask listTask){
        list.remove(listTask);
        return true;
    }
}
