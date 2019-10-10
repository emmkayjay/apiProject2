package com.example.springbootmonolith.Controller;

import com.example.springbootmonolith.Service.UserProfileService;
import com.example.springbootmonolith.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{username}")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    /**
     * For testing purposes
     * @param userProfileService
     */
    public void setUserProfileService(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }


    @GetMapping("/profile")
    public UserProfile getUserProfile(@PathVariable String username) {
        return userProfileService.getUserProfile(username);
    }

    @PostMapping("/profile")
    public  UserProfile createUserProfile(@PathVariable String username, @RequestBody UserProfile newProfile) {
        return userProfileService.createUserProfile(username, newProfile);
    }

}
