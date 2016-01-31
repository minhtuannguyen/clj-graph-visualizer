#!/bin/bash

for var in  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
do
	curl -X POST --data "parent=root&id=node$var" http://localhost:8080/addnode/
	curl -X POST --data "parent=node$var&id=child$var" http://localhost:8080/addnode/
done
