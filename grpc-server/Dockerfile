FROM maven:3.6.3-jdk-11 AS builder
WORKDIR /workdir/grpc-server
COPY pom.xml /workdir/grpc-server/pom.xml
RUN mvn dependency:go-offline

COPY src /workdir/grpc-server/src
RUN mvn install
RUN mkdir  -p target/depency
WORKDIR /workdir/grpc-server/target/dependency
RUN jar -xf ../*.jar

FROM openjdk:11-jre-slim

EXPOSE 8081
VOLUME /tmp
ARG DEPENDENCY=/workdir/grpc-server/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","io.github.huypva.grpcserver.GrpcServerApplication"]
