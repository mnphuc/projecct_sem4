package com.project.sem4.scheduling;

import com.project.sem4.model.service.ListTask;
import com.project.sem4.service.CheckTest;
import com.project.sem4.service.MailService;
import com.project.sem4.service.sendConfirmationMail;
import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendMail {
    @Autowired
    CheckTest checkTest;
    @Autowired
    sendConfirmationMail sendConfirmationMail;
    @Autowired
    MailService mailService;
    @Scheduled(fixedDelay = 3000)
    public void scheduleFixedDelayTask() throws InterruptedException {
        List<ListTask> listTasks = checkTest.getAllTask();
        if (!listTasks.isEmpty()){
            for (ListTask listTask : listTasks){
                //System.out.println("ahihi bố phúc"+listTask);
                if (listTask.getCheckTask() == 1){
                    sendConfirmationMail.sendConfirmationMail(listTask.getEmail(), listTask.getObject());
                    System.out.println("gửi mail thành công"+ listTask.getEmail());
                    checkTest.removeTask(listTask);
                }
                if (listTask.getCheckTask() == 2){
                    mailService.send(listTask);
                    System.out.println("gửi hóa Đơn thành công"+ listTask.getEmail());
                    checkTest.removeTask(listTask);
                }
                if (listTask.getCheckTask() == 3){
                    sendConfirmationMail.sendResetPasswordMail(listTask.getEmail(), listTask.getObject());
                    System.out.println("Đã gửi mail reset Mật khẩu"+ listTask.getEmail());
                    checkTest.removeTask(listTask);
                }
            }
        }
    }
}
