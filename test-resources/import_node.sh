#!/bin/bash

if [ -z $1 ]; then
  echo "number is mandatory"
  exit 1;
fi

count=1
limit=`echo "$1" | bc`

curl -X DELETE http://localhost:8080/node/

until [ ! $count -lt  $limit ]
do
    echo "add node $count"
	curl -X POST --data "parent=root&id=node$count" http://localhost:8080/node/
	curl -X POST --data "parent=node$count&id=child$count" http://localhost:8080/node/
	count=$((count+1))
done

echo "DONE!"