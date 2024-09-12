FROM openjdk:17
COPY target/api-gateway.war api-gateway.war
ENTRYPOINT ["java","-jar","/api-gateway.war"]
EXPOSE 9081