#Magnificent monitoring Service

### Prerequisites

```
Install Maven, JAVA JDK 8, and run magnificent python script
```
## Running the code

```
mvn clean compile
mvn exec:java -D"exec.mainClass"="MagnificentApplication"
```

## Sample output after running program

```
[INFO ] 2019-10-26 23:51:15.659 [MagnificentApplication.main()] MonitorService.Monitor - Server http://localhost:12345/ is healthy for 100.00 % in last 0.00 second
[WARN ] 2019-10-26 23:51:17.666 [MagnificentApplication.main()] MonitorService.Monitor - Server http://localhost:12345/ is not healthy for 50.00 % in last 2.05 second
[INFO ] 2019-10-26 23:51:19.668 [MagnificentApplication.main()] MonitorService.Monitor - Server http://localhost:12345/ is healthy for 66.67 % in last 4.06 second
[WARN ] 2019-10-26 23:51:21.672 [MagnificentApplication.main()] MonitorService.Monitor - Server http://localhost:12345/ is not healthy for 50.00 % in last 6.06 second
[INFO ] 2019-10-26 23:51:23.674 [MagnificentApplication.main()] MonitorService.Monitor - Server http://localhost:12345/ is healthy for 60.00 % in last 8.06 second
```

## Running the tests
```
mvn clean test
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Improvement

```
We can integrate spring boot for depedency injection and management of beans
```

