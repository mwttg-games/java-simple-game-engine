# Java Simple Game Engine

## Requirements
* Java 16
* Maven 3.8.1
* x64 OS (MacOs, Linux, Windows)

## Build the library
There are three Maven profiles
* lwjgl-natives-linux-amd64
* lwjgl-natives-macos-amd64
* lwjgl-natives-windows-amd64

Depending on your OS you can build the JavaSimpleGameLibrary by:
```
mvn clean install -P<profile>
```
Example:
```
mvn clean install -Plwjgl-natives-macos-amd64
```