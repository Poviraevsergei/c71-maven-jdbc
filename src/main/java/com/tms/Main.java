package com.tms;

import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        JdbcLesson jdbcLesson = new JdbcLesson();
        jdbcLesson.checkTransaction();

        System.out.println(jdbcLesson.deleteUser(2L));
    }
}
