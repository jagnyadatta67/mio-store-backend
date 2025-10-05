package com.miostore.auth;

import com.miostore.user.entity.User;
import com.miostore.user.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        // Extract Google attributes
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");
        // Check if user already exists
        User user = userRepository.findByEmail(email).orElseGet(() ->
                User.builder()
                        .email(email)
                        .name(name)
                        .passwordHash(null) // no password for Google login
                        .build()
        );
        user.setName(name);
        user.setProfilePicture(picture); // if you add this field
        user.setLastLoginAt(LocalDateTime.now());
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return oAuth2User;
    }
}
