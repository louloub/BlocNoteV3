package com.example.profilRTDBtoCF;

public enum Gender {
    HOMME("Homme"),
    FEMME("Femme");


    // The String value corresponding to this gender
    private final String gender;

    // Enum constructor
    Gender(final String genderAsString) {
        this.gender = genderAsString;
    }

    // Returns the String value corresponding to this ProfileStatus
    @Override
    public String toString() {
        return this.gender;
    }

    // Returns the ProfileStatus corresponding to the String given in parameter
    public static Gender valueFor(String genderAsString) {
        for (Gender currentGender : values()) {
            if (currentGender.gender.equals(genderAsString)) {
                return currentGender;
            }
        }
        return null;
    }}
