#!/usr/bin/env bash

cd ../../../

mvn clean
mvn install

scp target/daily-1.0-SNAPSHOT.jar ali:/root/web/daily

cd vue
npm run build
tar -czf vue.tar.gz dist
scp vue.tar.gz ali:/root/web/daily
