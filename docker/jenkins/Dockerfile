FROM jenkins:latest
 
USER root
RUN apt-get update
WORKDIR /tmp
RUN wget https://get.docker.com/builds/Linux/i386/docker-1.9.1.tgz
RUN tar -xf docker-1.9.1.tgz
RUN mv usr/local/bin/docker /usr/bin/
RUN chmod 777 /usr/bin/docker
RUN groupadd docker
RUN usermod -aG docker jenkins