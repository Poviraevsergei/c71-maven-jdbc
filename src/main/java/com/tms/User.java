package com.tms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //POJO - plain old java object (model)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String userPassword;
    private Timestamp created;
    private Timestamp changed;
    private Integer age;
}
