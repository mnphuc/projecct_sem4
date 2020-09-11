package com.project.sem4.scheduling;

import com.project.sem4.model.service.Mail;
import com.project.sem4.service.CheckTest;
import com.project.sem4.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class UserBirthday {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    CheckTest checkTest;
    public void checkBirthdayUser(){

    }

//    @Scheduled(fixedDelay = 1000)
//    public void scheduleFixedDelayTask() throws InterruptedException {
////        //List<Mail> list = checkTest.getAll();
////        if (list.isEmpty()){
////            System.out.println("Rỗng");
////        }else {
////            for (Mail mail : list){
////                list.remove(mail);
////            }
////            System.out.println(checkTest.getAll()+" - " + new Date());
////        }
//
//    }
//
//    @Scheduled(fixedRate = 2000)
//    public void scheduleFixedRateTask() throws InterruptedException {
//        System.out.println("Task2 - " + new Date());
//    }
//
//    @Scheduled(cron = "*/3 * * * * *")
//    public void scheduleTaskUsingCronExpression() throws InterruptedException {
//        System.out.println("Task3 - " + new Date());
//    }
}
