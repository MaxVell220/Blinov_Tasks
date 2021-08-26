package main.airport.entity;

import main.airport.pool.PlanePool;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Passenger extends Thread {
  private final static Logger LOG = Logger.getLogger(String.valueOf(Passenger.class));

  private boolean seating;
  private PlanePool pool;

  public Passenger(PlanePool pool){
    this.pool = pool;
    LOG.log(Level.INFO,"Passenger #" + this.getId() + " is created");
  }

  public void run(){
    this.setName("Passenger-" + this.getId());
    Plane plane = null;

    while (plane == null){
      LOG.log(Level.INFO," The passenger #" + this.getId() + "Is trying to find available seats in the planes ");
      plane = pool.getPlane(500);
    }
    LOG.info("Passenger №" + this.getId() + " ordered a seat in the plane №" + plane.getPlaneId() + ". Seating in №" + plane.getPlaneId());
    seating = true;

    plane.using();

    seating = false;
    LOG.info("Passenger №" + this.getId() + ". The plane №" + plane.getPlaneId() + " has arrived to the airport №" + plane.getAirportId());
    while (true){
      if (pool.returnPlane(plane)){
        break;
      }
    }
    LOG.log(Level.INFO,"Passenger #" + this.getId() + " has finished his travellings");
  }

  public boolean isSeating(){
    return seating;
  }
}
