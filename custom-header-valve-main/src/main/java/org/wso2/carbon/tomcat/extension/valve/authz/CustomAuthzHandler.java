package org.wso2.carbon.tomcat.extension.valve.authz;

import org.wso2.carbon.identity.auth.service.AuthenticationContext;
import org.wso2.carbon.identity.auth.service.AuthenticationRequest;
import org.wso2.carbon.identity.authz.service.AuthorizationContext;
import org.wso2.carbon.identity.authz.service.AuthorizationResult;
import org.wso2.carbon.identity.authz.service.AuthorizationStatus;
import org.wso2.carbon.identity.authz.service.exception.AuthzServiceServerException;
import org.wso2.carbon.identity.authz.service.handler.AuthorizationHandler;
import org.wso2.carbon.identity.core.util.IdentityUtil;
import org.wso2.carbon.tomcat.extension.valve.CustomRequestWrapper;
import org.wso2.carbon.tomcat.extension.valve.CustomValve;

public class CustomAuthzHandler extends AuthorizationHandler {
    @Override
    public AuthorizationResult handleAuthorization(AuthorizationContext authorizationContext) throws AuthzServiceServerException {
        
        AuthorizationResult result = super.handleAuthorization(authorizationContext);
        if (result.getAuthorizationStatus().equals(AuthorizationStatus.DENY)) {
            System.out.println();
            AuthenticationContext authnContext = (AuthenticationContext) IdentityUtil.threadLocalProperties.get()
                    .get(CustomValve.AUTH_CONTEXT);
            CustomRequestWrapper request = (CustomRequestWrapper) authnContext.getAuthenticationRequest().getRequest();
            String body = request.getBody();
            System.out.println(body);
        }
        return result;
    }

    @Override
    public String getName() {
        return "CustomAuthzHandler";
    }
}
