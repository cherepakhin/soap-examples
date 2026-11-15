#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"
PROJECT_DIR=/home/vasi/prog/java/soap/soap-examples/soap-apache-cxf-jax-ws
cd $PROJECT_DIR

echo "Using Java: $($JAVA_HOME/bin/java -version 2>&1 | head -1)"

# Compile
mvn clean compile -q

# Build classpath
CP="target/classes:$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q 2>/dev/null)"

# Start server in background
echo "Starting server..."
echo "CP: $CP"
nohup $JAVA_HOME/bin/java -cp "$CP" demo.hw.server.Server > /tmp/server.log 2>&1 &
SERVER_PID=$!
echo "Server PID: $SERVER_PID"

# Wait for server to be ready
for i in $(seq 1 30); do
    if ss -tlnp 2>/dev/null | grep -q ':9000' || netstat -tlnp 2>/dev/null | grep -q ':9000'; then
        echo "Server is ready on port 9000"
        break
    fi
    sleep 1
done

# Run client
echo "Running client..."
$JAVA_HOME/bin/java -cp "$CP" demo.hw.client.Client
RESULT=$?

# Cleanup server
kill $SERVER_PID 2>/dev/null
wait $SERVER_PID 2>/dev/null
exit $RESULT
