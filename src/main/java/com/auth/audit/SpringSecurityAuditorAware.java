package com.auth.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
            String principal = (String) auth.getPrincipal();
            Optional<String> username = Optional.ofNullable(principal);
            return username;
        } else {
            return Optional.ofNullable("OPEN_API").filter(s -> !s.isEmpty());
        }
    }
}
