package org.example.part4.chainOfResponsibility;

import org.example.part4.user.User;

public class UserRequest {
    private String username;
    private String password;
    private String userInfo;
    private User currentUser; // Кто делает запрос
    private boolean approved;
    private String rejectionReason;

    public UserRequest(String username, String password, String userInfo, User currentUser) {
        this.username = username;
        this.password = password;
        this.userInfo = userInfo;
        this.currentUser = currentUser;
        this.approved = false;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getUserInfo() { return userInfo; }
    public User getCurrentUser() { return currentUser; }
    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public void reject(String reason) {
        this.approved = false;
        this.rejectionReason = reason;
    }

    @Override
    public String toString() {
        return "UserRequest{username='" + username + "', currentUser=" +
                (currentUser != null ? currentUser.getName() : "null") + "}";
    }
}
