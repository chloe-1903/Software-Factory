#!/bin/bash

## Building client
cd client
mvn clean package
cd ..

## Building the j2e system
cd Modules
mvn clean package
cd ..

## Building the .Net system
cd dotNet
./compile.sh
cd ..
