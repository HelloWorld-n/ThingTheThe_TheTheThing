FROM thing
RUN echo "Hi!"
RUN echo ./mvnw clean install compile package generate-sources
RUN echo "Bye!"
