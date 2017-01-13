# Shipping cost calculator service

This service can calculate the shipping cost between the origin and the destination and offer 4 shipping options. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites


```
Clone the repository: https://github.com/CodecoolBP20161/from-python-to-java-microservices-nemecsekerrno.git
```

### Running the service


1. Go into the folder of the service: 
  - from-python-to-java-microservices-nemecsekerrno/src/main/java/com/codecool/shipping_cost_calculator_service/server
2. Run the ShippingCostCalculatorService.java file
3. The service is running on the 65011 port
4. You can check the server status on: [http://localhost:65011/status](http://localhost:65011/status)
5. You can make request like this: [http://localhost:65011/shipping-cost?origin=budapest&destination=erd](http://localhost:65011/shipping-cost?origin=budapest&destination=erd)
6. The response should look like this: 
```json
{"expressCourier": {"destinationFound":"Érd, Hungary","cost":60,"originFound":"Budapest, Hungary","details":"Express courier with fixed cost and time","currency":"USD","timeInHours":48,"distanceInKm":21},
"truck":{"destinationFound":"Érd, Hungary","cost":1,"originFound":"Budapest, Hungary","details":"Standard truck avoiding highways","currency":"USD","timeInHours":0,"distanceInKm":21},
"truckViaHighway":{"destinationFound":"Érd, Hungary","cost":22,"originFound":"Budapest, Hungary","details":"Standard truck via highway","currency":"USD","timeInHours":0,"distanceInKm":19},
"timeMachine":{"destinationFound":"Érd, Hungary","cost":6000000,"originFound":"Budapest, Hungary","details":"Most advanced technology, totally safe - we promise","currency":"USD","timeInHours":1,"distanceInKm":21}}
```



### Frequent error messages

1. Shipping cost calculator service:
    - Origin or destination cannot be empty or whitespaces only
    - Distance Matrix elements status message not implemented     
    - Distance Matrix status message not implemented
    
        
2. Google Distance Matrix API errors:
    - The application has requested too many elements within the allowed time period. 
        The request should succeed if you try again after a reasonable amount of time.
    - The service denied use of the Distance Matrix service by your web page.
    - A Distance Matrix request could not be processed due to a server error.
        The request may succeed if you try again.
    - No route could be found between the origin and destination.
    - The origin and/or destination of this pairing could not be geocoded.

## Built With

* [Spark](http://sparkjava.com/documentation.html) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Réka Nagy** - [rekan](https://github.com/rekan)
* **Gábor Seres** - [Shevah92](https://github.com/Shevah92)
* **György Hamar** - [hamargyuri](https://github.com/hamargyuri)
* **Lóránt Bereczki** - [berloc](https://github.com/berloc)




## License

This project is licensed under the nemecsek_errno team

## Acknowledgments

* Mentors of Codecool
* Family
* Friends
* Sponsors