FROM maven:3.3-jdk-8 AS builder

ARG SPRING_VERSION
ENV SPRING_VERSION ${SPRING_VERSION:-2.0.5.RELEASE}

WORKDIR /usr/src/spring
RUN wget --quiet https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/${SPRING_VERSION}/spring-boot-cli-${SPRING_VERSION}-bin.tar.gz && \
    tar xvzf spring-boot-cli-${SPRING_VERSION}-bin.tar.gz && \
    rm spring-boot-cli-${SPRING_VERSION}-bin.tar.gz
    
ENV PATH=${PATH}:/usr/src/spring/spring-${SPRING_VERSION}/bin