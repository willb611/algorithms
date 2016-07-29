# Algorithms

Some simple programs to solve common algorithmic problems.

## Building
Built using gradle. It should be distributed with a gradle wrapper.
https://docs.gradle.org/current/userguide/gradle_wrapper.html

Style checked using the plugin 'checkstyle' against Google Java code style.

## Graphs
The .dot format produced by the GraphIntoDotFormatConverter is a commonly used format for graphs. It can be converted into an image using [Graphviz](http://www.graphviz.org/).
 
If Graphviz is installed, and the path is setup correctly you can convert a .dot file into an image by running:
`dot -Tpng yourfile.dot -o outputfile.png`