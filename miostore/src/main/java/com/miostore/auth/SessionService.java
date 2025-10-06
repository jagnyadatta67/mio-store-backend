package com.miostore.auth;


import com.miostore.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * ✅ Thread-safe SessionService for storing the current authenticated user per request.
 */
@Component
public class SessionService {

    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public User getCurrentUser() {
        User user = currentUser.get();
        if (user == null) {
            throw new IllegalStateException("No user is set in the current session context.");
        }
        return user;
    }

    public void clear() {
        currentUser.remove();
    }
}
