package main.airport.pool;

import main.airport.entity.Airport;
import main.airport.entity.Plane;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlanePool {
  private final static Logger LOG = Logger.getLogger(String.valueOf(PlanePool.class));

  private ReentrantLock lock = new ReentrantLock();
  private ArrayList<Airport> pool = new ArrayList<>();



  public PlanePool(){
  }

  public void addToPool(Airport airport){
    pool.add(airport);
  }

  public Plane getPlane(long maxWaitMillis) {
    LOG.log(Level.INFO, "Passenger #" + Thread.currentThread().getId() + " tried to  take seat in a plane");
    Plane toReturn = null;
    for (Airport airport : pool) {
      Plane plane = airport.takeSeatInPlane(maxWaitMillis);
      if (plane != null){
        toReturn = plane;
        break;
      }
      else {
        LOG.info("Passenger #" + Thread.currentThread().getId() + " -> timeout. Missing plane.");
      }
    }
    return toReturn;
  }

  public boolean returnPlane(Plane plane){
    boolean toReturn = false;
    if (lock.tryLock()) {
      for (Airport airport : pool) {
        if (airport.getAirportId() == plane.getAirportId()) {
          airport.returnPlane(plane);
        }
      }
      toReturn = true;
      lock.unlock();
    }
    return toReturn;
  }
}
