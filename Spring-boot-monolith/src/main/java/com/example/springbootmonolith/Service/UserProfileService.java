package com.example.springbootmonolith.Service;

import com.example.springbootmonolith.model.UserProfile;

public interface UserProfileService {

    /**
     *
     * @param username
     * @param newProfile
     * @return
     */
    public UserProfile createUserProfile(String username, UserProfile newProfile);

    /**
     *
     * @param username
     * @return
     */
    public UserProfile getUserProfile(String username);
}
