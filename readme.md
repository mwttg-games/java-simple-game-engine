# Java Simple Game Engine

## Requirements

* Java 16
* Maven 3.8.1
* x64 OS (MacOs, Linux, Windows)

## Build the library

The `pom.xml` file contains a property `<lwjgl.natives>`. You can set this value to:

* natives-linux
* natives-macos
* natives-windows

depending on the OS where you build the library.

!!TODO!! find a way to pack ALL natives into the jar, so every OS can use this lib without
re-install

You can build the JavaSimpleGameLibrary by:

```
mvn clean install
```

This installs the dependency to your local `.m2` repository.