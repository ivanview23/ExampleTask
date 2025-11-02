package org.example.part4.builder;

import org.example.part4.user.RealUserManager;
import org.example.part4.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserManagerBuilder {
    private List<User> initialUsers = new ArrayList<>();
    private boolean withDefaultUsers = false;

    public UserManagerBuilder addUser(UserBuilder userBuilder) {
        this.initialUsers.add(userBuilder.build());
        return this;
    }

    public UserManagerBuilder addUser(User user) {
        this.initialUsers.add(user);
        return this;
    }

    public UserManagerBuilder addUser(String name, String password, String userInfo) {
        User user = UserBuilder.create(name, password)
                .withUserInfo(userInfo)
                .build();
        this.initialUsers.add(user);
        return this;
    }

    public UserManagerBuilder withDefaultUsers() {
        this.withDefaultUsers = true;
        return this;
    }

    public RealUserManager build() {
        RealUserManager userManager = new RealUserManager();

        for (User user : initialUsers) {
            userManager.addUser(user.getName(), user.getPassword(), user.getUserInfo());
        }

        return userManager;
    }

    public static UserManagerBuilder create() {
        return new UserManagerBuilder();
    }
}