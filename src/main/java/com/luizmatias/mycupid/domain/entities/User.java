package com.luizmatias.mycupid.domain.entities;

import java.util.Date;
import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private Date birth;
    private String email;
    private String password;
    private UserType userType;
    private UserRole userRole;
    private Double latitude;
    private Double longitude;
    private Boolean isEmailVerified;
    private List<User> likedUsers;
    private List<User> likedByUsers;
    private List<Match> matchesAsClient;
    private List<Match> matchesAsDeveloper;
    private List<UserTemporaryToken> userTemporaryTokens;
    private List<Interest> interests;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String bio, Date birth, String email, String password, UserType userType, UserRole userRole, Double latitude, Double longitude, Boolean isEmailVerified, List<User> likedUsers, List<User> likedByUsers, List<Match> matchesAsClient, List<Match> matchesAsDeveloper, List<UserTemporaryToken> userTemporaryTokens, List<Interest> interests) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userRole = userRole;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isEmailVerified = isEmailVerified;
        this.likedUsers = likedUsers;
        this.likedByUsers = likedByUsers;
        this.matchesAsClient = matchesAsClient;
        this.matchesAsDeveloper = matchesAsDeveloper;
        this.userTemporaryTokens = userTemporaryTokens;
        this.interests = interests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<User> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public List<User> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(List<User> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public List<Match> getMatchesAsClient() {
        return matchesAsClient;
    }

    public void setMatchesAsClient(List<Match> matchesAsClient) {
        this.matchesAsClient = matchesAsClient;
    }

    public List<Match> getMatchesAsDeveloper() {
        return matchesAsDeveloper;
    }

    public void setMatchesAsDeveloper(List<Match> matchesAsDeveloper) {
        this.matchesAsDeveloper = matchesAsDeveloper;
    }

    public List<UserTemporaryToken> getUserTemporaryTokens() {
        return userTemporaryTokens;
    }

    public void setUserTemporaryTokens(List<UserTemporaryToken> userTemporaryTokens) {
        this.userTemporaryTokens = userTemporaryTokens;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }
}
