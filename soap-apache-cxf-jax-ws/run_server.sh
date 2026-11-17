#!/bin/bash
clear
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"
PROJECT_DIR=/home/vasi/prog/java/soap/soap-examples/soap-apache-cxf-jax-ws
cd $PROJECT_DIR

echo "Using Java: $($JAVA_HOME/bin/java -version 2>&1 | head -1)"

# Compile
mvn clean compile -q

# Build classpath
CP="target/classes:$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q 2>/dev/null)"

# Start server
echo "Starting server on http://localhost:9000/helloWorld ..."
$JAVA_HOME/bin/java -cp "$CP" demo.hw.server.RunServer
