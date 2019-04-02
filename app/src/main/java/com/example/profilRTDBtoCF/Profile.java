package com.example.profilRTDBtoCF;

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
    private String currentVersion;
    private String email;
    private String employer;
    private String fullName;
    private String instanceID;
    private Date lastConnection;
    private static String nickname;
    private String oS;
    private Gender gender;
    private ProfileStatus status;
    private String ville;

    public Profile() {}

    // ----------------
    // Constructeur |
    // ----------------

    public Profile (String identifier, Integer age, String bio, Date birthday,
                   Date creationTime, String currentVersion,
                   String email, String employer, String fullName, String instanceID,
                    Date lastConnection, String nickname, String oS, Gender gender,
                    ProfileStatus status, String ville)
    {
        this.identifier = identifier;
        this.age = age;
        this.bio = bio;
        this.birthday = birthday;
        this.creationDate = creationTime;
        this.currentVersion = currentVersion;
        this.email = email;
        this.employer = employer;
        this.fullName = fullName;
        this.instanceID = instanceID;
        this.lastConnection = lastConnection;
        this.nickname = nickname;
        this.oS = oS;
        this.gender = gender;
        this.status = status;
        this.ville = ville;
    }

    public static String getNickname1(Profile profile) {
        return nickname;
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

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
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

    public Date getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }

    public static String getNickname() {
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
        result.put("currentVersion", currentVersion);
        result.put("email", email);
        result.put("employer", employer);
        result.put("fullName", fullName);
        result.put("instanceID", instanceID);
        result.put("lastConnection", lastConnection);
        result.put("nickname", nickname);
        result.put("oS", oS);
        result.put("gender", gender);
        result.put("status", status);
        result.put("ville", ville);

        return result;
    }
}