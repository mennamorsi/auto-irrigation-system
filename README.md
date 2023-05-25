# auto-irrigation-system
> Description

An auto irrigation system which helps the automatic irrigation of agricultural lands without human intervention with mocking sensors.

The application is designed and implemented using cutting edge technologies such as:

- Java 17
- Spring Boot 2.7.12
- Lombok
- Postgresql

> Installation 

Follow the commands below to install the application.

```bash 
$ git clone 
```

```bash 
$ mvn clean install
```

```bash 
$ mvn spring-boot:run
```

> How it Works?

We have 4 Endpoints for Plot:
- An endpoint to add a new plot of land
- An endpoint to configure a plot of land
- An endpoint to edit a plot of land
- An endpoint to get all plots

   > Add a new plot of land

       Request: POST http://localhost:8080/api/plot
       Request body:{
          "plotCode":"A120",
          "plotLength":12,
          "plotWidth":10,
          "plotType":"type A"
         }
        Response Body:{
          "id": 1,
          "plotCode": "A120",
          "plotLength": 12,
          "plotWidth": 10,
          "plotType": "type A"
         } 
      
    > Configure a plot of land

       Request: POST http://localhost:8080/api/plotConfigure
       Request body:{
           "plotId":1,
           "configuration":{
               "timeSpanInDays":5.5,
               "numberOfBlockedRetries":0,
               "retryFlag":true,
               "numberOfRetry":4
             }
          }
        Response Body:{
            "id": 2,
            "plotEntity": {
                "id": 1,
                "plotCode": "A120",
                "plotLength": 12,
                "plotWidth": 10,
                "plotType": "type A"
            },
            "timeSpanInDays": 5,
            "numberOfBlockedRetries": 0,
            "retryFlag": true,
            "numberOfRetry": 4,
            "cronId": "e276c3a6-963a-4623-948c-d1b5a8d45a7a"
          }
            
            
    > Edit a plot of land

       Request: PUT http://localhost:8080/api/plot/1
       Request body:{
            "plotCode":"A120",
            "plotLength":12,
            "plotWidth":10,
            "plotType":"type C"
         }
        Response Body:{
            "id": 1,
            "plotCode": "A120",
            "plotLength": 12,
            "plotWidth": 10,
            "plotType": "type C"
         } 
      
         
 Go to your browser http://localhost:8080/api/ and try any request.
