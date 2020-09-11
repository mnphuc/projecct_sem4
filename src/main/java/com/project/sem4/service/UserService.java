package com.project.sem4.service;

import com.project.sem4.model.Users;
import com.project.sem4.model.service.ConfirmationToken;
import com.project.sem4.model.service.ListTask;
import com.project.sem4.model.users.User;
import com.project.sem4.model.view.InsertUser;
import com.project.sem4.repository.ConfirmationTokenRepositoryImpl;
import com.project.sem4.repository.UserRepositoryImpl;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepositoryImpl userRepository;
    @Autowired
    ConfirmationTokenRepositoryImpl confirmationTokenRepository;
    @Autowired
    sendConfirmationMail sendConfirmationMail;
    @Autowired
    CheckTest checkTest;
    public Boolean signUpUser(InsertUser insertUser){
        Boolean bll = false;
        Boolean bl = userRepository.insertUserClient(insertUser);
        if (bl){
            Users users = userRepository.getUserByEmail(insertUser.getEmail());
            final ConfirmationToken confirmationToken = new ConfirmationToken(users);
            confirmationToken.setUser(users);
            confirmationToken.setUserId(users.getUserID());
            confirmationTokenRepository.addConfigToken(confirmationToken);
            ListTask listTask = new ListTask();
            listTask.setEmail(insertUser.getEmail());
            listTask.setObject(confirmationToken.getConfirmationToken());
            listTask.setCheckTask(1);
            checkTest.addTask(listTask);
            //sendConfirmationMail.sendConfirmationMail(insertUser.getEmail(), confirmationToken.getConfirmationToken());
            bll = true;
        }
        return bll;
    }

}
