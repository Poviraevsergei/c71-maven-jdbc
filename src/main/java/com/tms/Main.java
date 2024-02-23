package com.tms;

import com.tms.model.User;
import com.tms.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        System.out.println(userRepository.findAll());

/*        //Create
        userRepository.createUser(user);

        //Read
        User user = userRepository.findById(108L);

        //Update
        user.setUsername("NEW_USERNAME");
        userRepository.updateUser(user);

        //Delete
        userRepository.deleteUser(666L);*/
    }
}
