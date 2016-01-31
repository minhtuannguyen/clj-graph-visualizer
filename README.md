# clj-graph-visualizer

clj-graph-visualizer is just a experiment ti evaluate how [netjsongraph](https://github.com/interop-dev/netjsongraph.js) deals with a very big graph

## Installation
    $ ./lein.sh uberjar

## Usage

    $ java -jar clj-graph-visualizer1-0.1.0-standalone.jar

## Examples
Initialy the graph only has the root names "root".
    //to add a node to the graph
    $ curl -X POST --data "parent=root&id=nodename" http://localhost:8080/node/
    
    //to reset the graph, which then only has the root
    $curl -X DELETE http://localhost:8080/node/

the view can be seen under http://localhost:8080/view/
## License

Copyright © 2016

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.