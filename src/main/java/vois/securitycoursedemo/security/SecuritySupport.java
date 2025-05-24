package vois.securitycoursedemo.security;
import org.springframework.security.core.context.SecurityContextHolder;
import vois.securitycoursedemo.security.securedentities.SecurityUser;


public class SecuritySupport {
    public static Long getCurrentUserId() {
        SecurityUser securityUser = getCurrentUser();
        return securityUser.getId();
    }

    private static SecurityUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof SecurityUser securityUser)) {
            throw new IllegalStateException("Principal must be instanceof SecurityUser");
        }
        return securityUser;
    }

}