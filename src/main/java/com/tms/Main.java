package com.tms;

import com.tms.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        System.out.println(userRepository.truncateTelephoneTableFunction());
    }
}
