package com.hua.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class UserAuthenticationHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
                                        Authentication authentication) throws IOException {

        boolean hasCitizenRole = false;
        boolean hasAdminRole = false;
        boolean hasEmployeeRole = false;


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            switch (grantedAuthority.getAuthority()) {
                case "ROLE_CITIZEN":
                    hasCitizenRole = true;
                    break;
                case "ROLE_ADMIN":
                    hasAdminRole = true;
                    break;
                case "ROLE_EMPLOYEE":
                    hasEmployeeRole = true;
                    break;
            }
        }

        if (hasCitizenRole) {
            redirectStrategy.sendRedirect(arg0, arg1, "api/citizen/viewMenuCitizen");
        } else if (hasAdminRole) {
            redirectStrategy.sendRedirect(arg0, arg1, "api/admin/viewMenu");
        } else if(hasEmployeeRole) {
            redirectStrategy.sendRedirect(arg0, arg1, "api/employee/viewMenuEmployee");
        } else {
            throw new IllegalStateException();
        }
    }
}
