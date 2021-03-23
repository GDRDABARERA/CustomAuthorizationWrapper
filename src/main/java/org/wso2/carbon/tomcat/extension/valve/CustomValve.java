package org.wso2.carbon.tomcat.extension.valve;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.wso2.carbon.identity.auth.service.AuthenticationContext;
import org.wso2.carbon.identity.authz.valve.AuthorizationValve;
import org.wso2.carbon.identity.core.util.IdentityUtil;


import javax.servlet.ServletException;

import java.io.IOException;


public class CustomValve extends AuthorizationValve {


    private static final Log log = LogFactory.getLog(CustomValve.class);
    public static final String AUTH_CONTEXT = "auth-context";

    public void invoke(Request request, Response response) throws IOException, ServletException {

        try {
            CustomRequestWrapper requestWrapper = new CustomRequestWrapper(request);
            setThreadLocalAuthnContext((AuthenticationContext) requestWrapper.getAttribute(AUTH_CONTEXT));
            getNext().invoke(requestWrapper, response);
        } catch(Exception ex) {
            log.warn("Error");
        } finally {
            unsetThreadLocalAuthnContext();
        }

    }

    private void setThreadLocalAuthnContext(AuthenticationContext authenticationContext) {

        Object authnContext = authenticationContext.getParameter(AUTH_CONTEXT);
        if (authnContext != null) {
            IdentityUtil.threadLocalProperties.get().put(AUTH_CONTEXT, authnContext);
        }
    }

    private void unsetThreadLocalAuthnContext() {

        IdentityUtil.threadLocalProperties.get().remove(AUTH_CONTEXT);
    }


}
