package com.example.profilRTDBtoCF;

enum ProfileStatus {
    // The user has created its account and is waiting for approval by an admin
    SUBMITTED("submitted"),
    // The user has been approved by an admin and can use its account
    APPROVED("approved"),
    // The user has been reported by another user for a specific reason
    REPORTED("reported"),
    // The user has been locked by an admin after being reported by a certain number of users
    LOCKED("locked"),
    // The user account has been deactivated by an admin after investigation because he was locked (or reported)
    DEACTIVATED("deactivated"),
    // The user has deleted its account
    DELETED("deleted"),
    // The user has not used the application since X days
    INACTIVE("inactive"),
    // The user status is incorrect
    UNKNOWN("unknown");

    // The String value corresponding to this ProfileStatus
    private final String profileStatusAsString;

    // Enum constructor
    ProfileStatus(final String profileStatusAsString) {
        this.profileStatusAsString = profileStatusAsString;
    }

    // Returns the String value corresponding to this ProfileStatus
    @Override
    public String toString() {
        return this.profileStatusAsString;
    }

    // Returns the ProfileStatus corresponding to the String given in parameter
    public static ProfileStatus valueFor(String profileStatusAsString) {
        for (ProfileStatus profileStatus : values()) {
            if (profileStatus.profileStatusAsString.equals(profileStatusAsString)) {
                return profileStatus;
            }
        }
        return UNKNOWN;
    }
}
