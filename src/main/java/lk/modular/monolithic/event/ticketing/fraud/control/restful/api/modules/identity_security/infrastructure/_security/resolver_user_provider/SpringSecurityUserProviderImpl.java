package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security.resolver_user_provider;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security._user_wrapper.CustomUserDetails;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.UnauthorizedException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.web_resolver.resolver.SpringSecurityUserProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUserProviderImpl implements SpringSecurityUserProvider {

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUser().getUserId();
        }

        throw new UnauthorizedException("User not authenticated");
    }
}
