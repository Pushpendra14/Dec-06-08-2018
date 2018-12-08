FROM java:8
VOLUME /tmp
ADD Day03Server-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 6001
ENTRYPOINT ["java","-Dserver.port=6001","-Dspring.profiles.active=native","-jar","/app.jar"]
