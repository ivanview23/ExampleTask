package org.example.part4.adapter;

class ExternalUser {
    private String login;
    private String fullName;
    private String profile;
    private String credentials;

    public ExternalUser(String login, String fullName, String profile, String credentials) {
        this.login = login;
        this.fullName = fullName;
        this.profile = profile;
        this.credentials = credentials;
    }

    public String getLogin() { return login; }
    public String getFullName() { return fullName; }
    public String getProfile() { return profile; }
    public String getCredentials() { return credentials; }
}