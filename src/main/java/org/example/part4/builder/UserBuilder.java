package org.example.part4.builder;

import org.example.part4.user.User;

public class UserBuilder {
    private long id;
    private String name;
    private String password;
    private String userInfo;

    public UserBuilder(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static UserBuilder create(String name, String password) {
        return new UserBuilder(name, password);
    }

    public UserBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withUserInfo(String userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public User build() {
        User user = new User(name, password);
        if (id != 0) {
            user.setId(id);
        }
        if (userInfo != null) {
            user.setUserInfo(userInfo);
        }
        return user;
    }
}