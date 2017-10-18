# java-8-concurrency

## Requirements
- maven
- java 8

## Description
Sample projects for playing around with java 8 concurrency utils. In particular, it uses Executors and Callable.

The application simulates 3 long running services by instantiating a custom Callable class that waits for a random N milliseconds
before returning an array of values.

These "services" are then called in parallel with an executor and a parallelStream and sequentially with a stream.
It is interesting to notice that running with a parallelStream is almost as performant as with the executor; while the code is significantly easier to read and maintain.  

## How to run the tests
mvn test
