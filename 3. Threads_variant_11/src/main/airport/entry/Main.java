package main.airport.entry;

import main.airport.creator.Creator;
import main.airport.entity.Passenger;
import main.airport.pool.PlanePool;

public class Main {

  public static void main(String[] args) {
    PlanePool pool = new PlanePool();
    pool.addToPool(Creator.createAirport0());
    pool.addToPool(Creator.createAirport1());
    pool.addToPool(Creator.createAirport2());

    for (int i = 0; i < 200; i++) {
      new Passenger(pool).start();
    }

  }

}
