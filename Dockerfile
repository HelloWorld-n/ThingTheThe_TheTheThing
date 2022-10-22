FROM thing
RUN echo "Hi!"
RUN /etc/init.d/postgresql start
RUN echo "Bye!"
