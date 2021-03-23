package org.wso2.carbon.tomcat.extension.valve;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

public class ResponseHeader extends ValveBase {

    private static final Logger logger = Logger.getLogger(ResponseHeader.class.getName());

    public void invoke(Request request, Response response) throws IOException, ServletException {
        final Logger logger = Logger.getLogger(ResponseHeader.class.getName());

        try {
            response.addHeader("Content-Security-Policy","default-src 'self'");
             getNext().invoke(request, response);
        } catch(Exception ex) {
            logger.warning(ex.toString());
        }

    }

}

