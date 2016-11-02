# stylish-converter

## Description
  * This service is using a light weight micro framework called [SparkJava](http://sparkjava.com//).
  * As it expose two APIs
      * `/USD` which will display a list of all rates against the given currency.
      * `/USD/EUR/4` which will convert from 4 USD to EUR.
  * The service has an initial default `3 currencies EUR,USD,JPY` from [fixer.io](http://fixer.io/).
  * The service uses an in-memory cache `redis` if it's available, and if not available it will get from a public available service called [fixer.io](http://fixer.io/).

## Note
This service must come with a daily cronjob which delete the stored data inside redis by calling this command,
because the new data will be published at 4.00pm CET on the website.
```
java -cp stylish-converter.jar:. com.stylish.converter.util.DataFlushUtil
```

## Package
```
java -jar stylish-converter.jar
```

## Server
```
http://localhost:4567
```

## Enhancement:
We might save the data into a json file and read from there when the cache not available.
