package com.tms;

import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        JdbcLesson jdbcLesson = new JdbcLesson();
        User user = new User(14L,
                "Antonio",
                "qwerty123",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                30);
        System.out.println(jdbcLesson.deleteUser(1L));
    }
}
