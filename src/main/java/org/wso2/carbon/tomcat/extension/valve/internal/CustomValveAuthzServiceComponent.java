package org.wso2.carbon.tomcat.extension.valve.internal;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.Request;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.wso2.carbon.identity.authz.service.handler.AuthorizationHandler;
import org.wso2.carbon.identity.core.handler.HandlerComparator;
import org.wso2.carbon.tomcat.extension.valve.CustomRequestWrapper;
import org.wso2.carbon.tomcat.extension.valve.CustomValve;
import org.wso2.carbon.tomcat.extension.valve.authz.CustomAuthzHandler;

import java.util.Collections;
import java.util.List;

@Component(
        name = "org.wso2.carbon.tomcat.extension.valve",
        immediate = true)
public class CustomValveAuthzServiceComponent {

    private static final Log log = LogFactory.getLog(CustomValveAuthzServiceComponent.class);

    @Activate
    protected void activate(ComponentContext cxt) {

        try {
            CustomAuthzHandler customAuthzHandler = new CustomAuthzHandler();
            cxt.getBundleContext().registerService(CustomAuthzHandler.class, customAuthzHandler, null);
            if (log.isDebugEnabled())
                log.debug("AuthorizationServiceComponent is activated");
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }

        log.info("================CustomValveAuthzServiceComponent activated ====================");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("AuthorizationServiceComponent bundle is deactivated");
        }
        log.info("================CustomValveAuthzServiceComponent deactivated ====================");
    }

}
