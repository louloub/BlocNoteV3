package com.example.profilRTDBtoCF;

import android.os.Parcel;

import java.util.Date;
import java.util.HashMap;

public class Profile {

    // ----------------
    // Attributs |
    // ----------------

    private String identifier;
    private Integer age;
    private String bio;
    private Date birthday;
    private Date creationDate;
    private String currentAppVersion;
    private String email;
    private String employer;
    private String fullName;
    private String instanceID;
    private Date lastConnectionTime;
    private String nickname;
    private String oS;
    private Gender gender;
    private ProfileStatus status;
    private String ville;
    private int secretCode;
    private int difficulty;

    public Profile() {}

    // ----------------
    // Constructeur |
    // ----------------

    public Profile (Integer age, String bio, Date birthday,
                   Date creationTime, String currentAppVersion,
                   String email, String employer, String fullName, String instanceID,
                    Date lastConnectionTime, String nickname, String oS, Gender gender,
                    ProfileStatus status, String ville, int secretCode, int difficulty)
    {
        this.identifier = identifier;
        this.age = age;
        this.bio = bio;
        this.birthday = birthday;
        this.creationDate = creationTime;
        this.currentAppVersion = currentAppVersion;
        this.email = email;
        this.employer = employer;
        this.fullName = fullName;
        this.instanceID = instanceID;
        this.lastConnectionTime = lastConnectionTime;
        this.nickname = nickname;
        this.oS = oS;
        this.gender = gender;
        this.status = status;
        this.ville = ville;
        this.secretCode = secretCode;
        this.difficulty = difficulty;
    }

    // ----------------
    // Getters Setters|
    // ----------------

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCurrentAppVersion() {
        return currentAppVersion;
    }

    public void setCurrentAppVersion(String currentAppVersion) {
        this.currentAppVersion = currentAppVersion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(String instanceID) {
        this.instanceID = instanceID;
    }

    public Date getLastConnectionTime() {
        return lastConnectionTime;
    }

    public void setLastConnectionTime(Date lastConnectionTime) {
        this.lastConnectionTime = lastConnectionTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getoS() {
        return oS;
    }

    public void setoS(String oS) {
        this.oS = oS;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(int secretCode) {
        this.secretCode = secretCode;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    // ----------------
    // HASHMAP        |
    // ----------------

    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("identifier", identifier);
        result.put("age", age);
        result.put("bio", bio);
        result.put("birthday", birthday);
        result.put("creationDate", creationDate);
        result.put("currentAppVersion", currentAppVersion);
        result.put("email", email);
        result.put("employer", employer);
        result.put("fullName", fullName);
        result.put("instanceID", instanceID);
        result.put("lastConnectionTime", lastConnectionTime);
        result.put("nickname", nickname);
        result.put("oS", oS);
        result.put("gender", gender);
        result.put("status", status);
        result.put("ville", ville);
        result.put("secretCode", secretCode);
        result.put("difficulty", difficulty);
        return result;
    }
}