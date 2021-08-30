FROM maven:3.6.3-jdk-11 AS builder
WORKDIR /workdir/grpc-client
COPY pom.xml /workdir/grpc-client/pom.xml
RUN mvn dependency:go-offline

COPY src /workdir/grpc-client/src
RUN mvn install
RUN mkdir  -p target/depency
WORKDIR /workdir/grpc-client/target/dependency
RUN jar -xf ../*.jar

FROM openjdk:11-jre-slim

EXPOSE 8081
VOLUME /tmp
ARG DEPENDENCY=/workdir/grpc-client/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","io.codebyexample.grpcclient.GrpcClientApplication"]
