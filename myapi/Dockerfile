# == Stage 1: Build and package the application
FROM maven:3.3-jdk-8 AS builder
WORKDIR /usr/src/work
# First copy only the pom and load dependencies. This will be cached by docker as a reusable layer.
COPY pom.xml .
RUN mvn dependency:go-offline -Dmaven.repo.local=/usr/src/repo
# Now copy the rest and call the package goal.
COPY . .
RUN mvn package -Dmaven.repo.local=/usr/src/repo

# == Stage 2: Copy the application into the production image
FROM tomcat:alpine
WORKDIR /usr/local/tomcat/webapps
COPY --from=builder /usr/src/work/target/myapi.war .
RUN mv myapi.war ROOT.war && rm -rf ROOT
CMD ["catalina.sh", "run"]