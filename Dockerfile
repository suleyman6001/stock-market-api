# This line is for initializing a basic linux environment with Java JDK21 in it.
FROM eclipse-temurin:21-jre

# This line creates the current working directory inside the container
WORKDIR /app

# This line copies the jar file from my host machine (from target directory) into the container filesystem,
# renames it stock_api_app.jar and places it inside /app because of WORKDIR
COPY target/*.jar stock_api_app.jar

# This line declares that the container will expose port 8081. This is mostly for documentation.
EXPOSE 8081

# This line is the runtime command. When docker runs the application, it executes java -jar stock_api_app.jar
ENTRYPOINT ["java", "-jar", "stock_api_app.jar"]