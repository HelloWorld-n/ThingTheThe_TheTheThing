FROM postgres
RUN /etc/init.d/postgres start

FROM thing
RUN echo "Hi!"
RUN ./mvnw clean install compile package generate-sources
RUN echo "Bye!"
