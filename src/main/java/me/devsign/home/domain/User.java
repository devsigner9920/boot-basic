package me.devsign.home.domain;

import lombok.Getter;

@Getter
public class User {
    private String id;
    private String password;
    private String name;
    private String address;
    private String ip;
}