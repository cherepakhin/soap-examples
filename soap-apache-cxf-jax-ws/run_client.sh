#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"
PROJECT_DIR=/home/vasi/prog/java/soap/soap-examples/soap-apache-cxf-jax-ws
cd $PROJECT_DIR

CP="target/classes:$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q 2>/dev/null)"

# for debug
echo "Class path (CP) :" $CP

# Run client
echo "Running client..."
$JAVA_HOME/bin/java -cp "$CP" demo.hw.client.Client

