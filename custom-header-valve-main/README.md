# custom-header-valve

Tomcat valve for adding Content-Security-Policy (CSP) header to IS-5.7.0

# Build, Deploy & Run

## Build
Execute the following command to build the project

```mvn clean install```

## Deploy

Copy and place the built JAR artifact from the /target/ custom-header-valve-1.0.jar to the <IS_HOME>/repository/components/lib directory. Navigate to <IS_HOME>/repository/conf//tomcat/catalina-server.xml and add the following valve configuration.

```<Valve className="org.wso2.carbon.tomcat.extension.valve.ResponseHeader"/>```

## Run

Restart the server. Now the 'Content-Security-Policy' should be available in the responses.
