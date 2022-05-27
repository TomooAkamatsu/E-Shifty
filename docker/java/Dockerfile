FROM ubuntu:20.04

RUN apt update
RUN apt -y upgrade
RUN apt -y install openjdk-17-jdk
ENV JAVA_HOME /usr/lib/jvm/java-1.17.0-openjdk-arm64
ENV PATH $JAVA_HOME/bin:$PATH
VOLUME /tmp
RUN mkdir /app
WORKDIR /app