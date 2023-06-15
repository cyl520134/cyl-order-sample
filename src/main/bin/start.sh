#!/bin/bash

# 设置环境变量
#export JAVA_HOME=/path/to/java/home
export APP_HOME=/Users/apple/IdeaProjects/cyl-order-sample/target/cyl-order-sample-0.0.1-SNAPSHOT-20230614
export CLASSPATH=$APP_HOME/cyl-order-sample-0.0.1-SNAPSHOT.jar:$APP_HOME/lib/*

# 启动命令
java -cp "$CLASSPATH" com.cyl.example.CylOrderSampleApplication

