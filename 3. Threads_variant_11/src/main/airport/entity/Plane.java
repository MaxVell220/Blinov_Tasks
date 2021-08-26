package main.airport.entity;

import main.airport.constants.Destination;
import main.airport.constants.Distance;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plane {

  private final static Logger LOG = Logger.getLogger(String.valueOf(Plane.class));

  private int planeId;
  private Distance flightDistance;
  private Destination destination;
  private int airportId;
  private int passengersCapacity;

  public Plane(int planeId, int airportId, Distance flightDistance, int passengersCapacity, Destination destination) {
    super();
    this.planeId = planeId;
    this.airportId = airportId;
    this.flightDistance = flightDistance;
    this.passengersCapacity = passengersCapacity;
    this.destination = destination;
  }

  public int getPlaneId() {
    return planeId;
  }

  public int getAirportId() {
    return airportId;
  }

  public void setPlaneId(int planeId) {
    this.planeId = planeId;
  }

  public void using() {
    addPassenger();
    LOG.log(Level.INFO, "plane #" + planeId + " is used by the passenger #" + Thread.currentThread().getId() + ". The passenger on the way to " + destination);
    try {
      if (passengersCapacity == 0) {
        TimeUnit.MILLISECONDS.sleep(new java.util.Random().nextInt(this.flightDistance.getValue()));
      }
    } catch (InterruptedException e) {
      LOG.log(Level.INFO, e.toString());
    }
  }

  public void addPassenger() {
    if (passengersCapacity >= 0) {
      passengersCapacity--;
    }
  }

  @Override
  public String toString() {
    return "plane #" + planeId + " AIRPORT_ID #" + airportId;
  }

}
