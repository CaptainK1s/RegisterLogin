package com.example.registerlogin;

public class User {
    private String fullName;
    private String gender;
    private int birthYear;
    private String username;
    private String password;

    public User(String fullName, String gender, int birthYear, String username, String password) {
        this.fullName = fullName;
        this.gender = gender;
        this.birthYear = birthYear;
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getFullName() { return fullName; }
    public String getGender() { return gender; }
    public int getBirthYear() { return birthYear; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public String getUserInfo() {
        return "Name: " + fullName + "\n" +
               "Gender: " + gender + "\n" +
               "Birth Year: " + birthYear + "\n" +
               "Username: " + username;
    }
}
