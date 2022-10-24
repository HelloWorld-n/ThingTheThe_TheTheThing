FROM postgres
RUN echo "[!!!]"

FROM thing
RUN ./mvnw clean install compile package generate-sources

FROM node:12-alpine
# Adding build tools to make yarn install work on Apple silicon / arm64 machines

RUN apk add --no-cache maven

WORKDIR /app
RUN yarn install --production
RUN ./mvnw exec:java@someID"
