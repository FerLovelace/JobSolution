FROM openjdk:17
VOLUME /tmp
#ENV IMG_PATCH=/img 
EXPOSE 9191
#RUN mkdir -p /img
ARG JAR_FILE=target/appjob-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT [ "java","-jar","/app.jar" ]