FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=target/*.jar
ARG PROFILES
# target/*.jar 파일을 복사한것
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}", "-jar", "app.jar"]
# "-Dspring.profiles.active=${PROFILES}" 에서 "-Dspring.profiles.active=blue" 일때 yml에 server 가 blue로 바뀜