FROM openjdk:8-jdk-slim
COPY "./out/artifacts/GetInventory_jar/GetInventory.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]