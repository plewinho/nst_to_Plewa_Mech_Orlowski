package com.main.casinoapp;

public class User {
    private int userId;
    private String email;
    private String password;
    private String nickname;
    private int age;
    private int balance; // Dodano pole do przechowywania salda użytkownika

    private static User currentUser = null;

    public User(int userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.nickname = nickname;
        this.age = age;
    }
    public int getBalance() {
        return balance;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Metoda do pobierania aktualnie zalogowanego użytkownika
    public static User getCurrentUser() {
        return currentUser;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public int getUserId() {
        return userId;
    }
}
