# Shipping cost calculator service

This service can calculate the shipping cost between origin(s) and destination(s) and offer 4 shipping options. 

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
6. The response should look like this: ![](https://github.com/CodecoolBP20161/from-python-to-java-microservices-nemecsekerrno/blob/storyID_42/src/main/java/com/codecool/shipping_cost_calculator_service/shippingserviceresponse.png)

### Frequent error messages

1. ```"Origin or destination cannot be empty or whitespaces only"```
 - request without origin or destination
2. ```""No route could be found between the origin and destination.""```
 - invalid origin or destination
3. ```"No ."```
 - request without origin or destination



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