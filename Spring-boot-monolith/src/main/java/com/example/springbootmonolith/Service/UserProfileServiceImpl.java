package com.example.springbootmonolith.Service;

import com.example.springbootmonolith.Repository.UserProfileRepository;
import com.example.springbootmonolith.model.User;
import com.example.springbootmonolith.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;

    /**
     *
     * @param username
     * @param newProfile
     * @return
     */
    @Override
    public UserProfile createUserProfile(String username, UserProfile newProfile) throws Exception {
        User user = userService.getUser(username);
        newProfile.setUser(user);
        user.setUserProfile(newProfile);
        if (user != null){
            return userProfileRepository.save(newProfile);
        }else {
            throw new Exception("You must be logged in to update your profile.");
        }
    }

    /**
     * Method continues to return user ID as null.  database reflects user ID in user profile table.
     * @param username
     * @return
     */
    @Override
    public UserProfile getUserProfile(String username) {
        return userProfileRepository.findProfileByUsername(username);
    }
}

