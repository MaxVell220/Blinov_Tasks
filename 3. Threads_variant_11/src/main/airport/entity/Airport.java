package main.airport.entity;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Airport {
  private final static Logger LOG = Logger.getLogger(String.valueOf(Airport.class));

  public int airportId;
  private int airportSize;
  private Queue<Plane> planes = new ConcurrentLinkedQueue<>();
  private Semaphore semaphore;

  public Airport(int airportId, int airportSize){
    this.airportId = airportId;
    this.airportSize = airportSize;
    this.semaphore = new Semaphore(airportSize, true);
  }

  public int getAirportId() {
    return airportId;
  }

  public void addPlane(Plane plane){
    if (airportSize >= 0){
      planes.add(plane);
      airportSize--;
    }
  }

  public Plane takeSeatInPlane(long maxWaitMillis){
    Plane toReturn = null;
    try {
      if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
        LOG.log(Level.INFO,"Passenger #" + Thread.currentThread().getId() + "  is seating in the plain #" + Airport.this.airportId);
        Plane plane = planes.poll();
        LOG.log(Level.INFO,"Passenger #" + Thread.currentThread().getId() +  " in the plane " + plane);
        toReturn = plane;
      }
    } catch (InterruptedException e) {
      LOG.log(Level.INFO, e.getMessage());
    }
    return toReturn;
  }

  public void returnPlane(Plane plane){
    planes.add(plane);
    semaphore.release();
  }
}
