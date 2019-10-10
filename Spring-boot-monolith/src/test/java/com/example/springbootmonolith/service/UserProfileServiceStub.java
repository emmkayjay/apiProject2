package com.example.springbootmonolith.service;

import com.example.springbootmonolith.Service.UserProfileService;
import com.example.springbootmonolith.model.UserProfile;

public class UserProfileServiceStub implements UserProfileService {
    @Override
    public UserProfile createUserProfile(String username, UserProfile newProfile) {
        UserProfile userProfile = new UserProfile();
        userProfile.setLocation("Queens, NY");
        userProfile.setBio("Test Profile Bio");
        userProfile.setMobile("111-111-1111");

        return userProfile;
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return null;
    }
}
