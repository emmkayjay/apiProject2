package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.Controller.UserProfileController;
import com.example.springbootmonolith.model.UserProfile;
import com.example.springbootmonolith.service.UserProfileServiceStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserProfileControllerTest {

    private UserProfileController userProfileController;


    @Before
    public void initUserProfileController() {
        userProfileController = new UserProfileController();
        userProfileController.setUserProfileService(new UserProfileServiceStub());
    }

    @Test
    public void createUserProfile_SavesUserProfile_Success() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setLocation("Queens, NY");
        userProfile.setBio("Test Profile Bio");
        userProfile.setMobile("111-111-1111");

        UserProfile newProfile = userProfileController.createUserProfile("TestUser", userProfile);

        Assert.assertNotNull(newProfile);
        Assert.assertEquals(newProfile.getLocation(), userProfile.getLocation());
        Assert.assertEquals(newProfile.getBio(), userProfile.getBio());
        Assert.assertEquals(newProfile.getMobile(), userProfile.getMobile());
    }

    @Test
    public void createUserProfile_SavesUserProfile_Failure() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setLocation("Bronx, NY");
        userProfile.setBio("Test Profile Bio Fail");
        userProfile.setMobile("222-222-2222");

        UserProfile newProfile = userProfileController.createUserProfile("TestUser", userProfile);

        Assert.assertNotNull(newProfile);
        Assert.assertEquals(newProfile.getLocation(), userProfile.getLocation());
        Assert.assertEquals(newProfile.getBio(), userProfile.getBio());
        Assert.assertEquals(newProfile.getMobile(), userProfile.getMobile());
    }
}
