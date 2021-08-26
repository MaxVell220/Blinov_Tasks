package main.airport.creator;

import main.airport.constants.Destination;
import main.airport.constants.Distance;
import main.airport.entity.Airport;
import main.airport.entity.Plane;

public class Creator {

  public static Airport createAirport0(){
    Airport airport = new Airport(0,5);
    airport.addPlane(new Plane(1, airport.getAirportId(), Distance.MEDIUM, 4, Destination.LONDON));
    airport.addPlane(new Plane(2, airport.getAirportId(),Distance.MEDIUM, 3,  Destination.NEW_YORK));
    airport.addPlane(new Plane(3, airport.getAirportId(),Distance.HIGH, 1,  Destination.MADRID));
    airport.addPlane(new Plane(4, airport.getAirportId(),Distance.LOW, 4,  Destination.MOSCOW));
    airport.addPlane(new Plane(5, airport.getAirportId(),Distance.MEDIUM, 10,  Destination.ROME));

    return airport;
  }

  public static Airport createAirport1(){
    Airport airport = new Airport(1,6);

    airport.addPlane(new Plane(1, airport.getAirportId(), Distance.MEDIUM, 4, Destination.LONDON));
    airport.addPlane(new Plane(2, airport.getAirportId(),Distance.MEDIUM, 3,  Destination.NEW_YORK));
    airport.addPlane(new Plane(3, airport.getAirportId(),Distance.HIGH, 1,  Destination.MADRID));
    airport.addPlane(new Plane(4, airport.getAirportId(),Distance.LOW, 4,  Destination.MOSCOW));
    airport.addPlane(new Plane(5, airport.getAirportId(),Distance.MEDIUM, 10,  Destination.ROME));
    airport.addPlane(new Plane(6, airport.getAirportId(),Distance.LOW, 3,  Destination.LONDON));

    return airport;
  }

  public static Airport createAirport2(){
    Airport airport = new Airport(2,7);

    airport.addPlane(new Plane(1, airport.getAirportId(), Distance.MEDIUM, 4, Destination.LONDON));
    airport.addPlane(new Plane(2, airport.getAirportId(),Distance.MEDIUM, 3,  Destination.NEW_YORK));
    airport.addPlane(new Plane(3, airport.getAirportId(),Distance.HIGH, 1,  Destination.MADRID));
    airport.addPlane(new Plane(4, airport.getAirportId(),Distance.LOW, 4,  Destination.MOSCOW));
    airport.addPlane(new Plane(5, airport.getAirportId(),Distance.MEDIUM, 10,  Destination.ROME));
    airport.addPlane(new Plane(6, airport.getAirportId(),Distance.LOW, 3,  Destination.LONDON));
    airport.addPlane(new Plane(7, airport.getAirportId(),Distance.LOW, 2,  Destination.LONDON));

    return airport;
  }


}
