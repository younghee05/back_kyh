FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=target/*.jar
ARG PROFILES
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}","-jar","app.jar"]
# ${PROFILES} ${NEW} YML 설정에 있는 값을 반영하는 것 active 에는 어떤 서버를 사용할건지 선택한다