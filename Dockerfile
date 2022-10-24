FROM thing
WORKDIR /app
RUN ./mvnw clean install compile package generate-sources
RUN yarn install --production
RUN ./mvnw exec:java@someID"
