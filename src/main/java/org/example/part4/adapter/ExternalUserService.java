package org.example.part4.adapter;

public interface ExternalUserService {
    String getUserDetails(String userLogin);
    void createUser(String login, String credentials, String profile);
    boolean removeUser(String userLogin);
    String[] listAllUsers();
}