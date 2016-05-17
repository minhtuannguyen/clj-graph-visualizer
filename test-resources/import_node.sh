#!/bin/bash

if [ -z $1 ]; then
  echo "number is mandatory"
  exit 1;
fi

if [ -z $2 ]; then
    echo "host is mandatory"	
    exit 1;
fi

host=$2
count=1
limit=`echo "$1" | bc`


curl -X DELETE $host/node/

until [ ! $count -lt  $limit ]
do
    echo "add node $count"
	curl -X POST --data "parent=root&id=node$count" $host/node/
	curl -X POST --data "parent=node$count&id=child$count" $host/node/
	count=$((count+1))
done

echo "DONE!"
