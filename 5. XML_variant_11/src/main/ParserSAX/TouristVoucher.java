package main.ParserSAX;


public class TouristVoucher {

  public Hotel hotel = new Hotel();
  private String id, country, transport, type;
  private Double cost;
  private Integer days;

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setTransport(String transport) {
    this.transport = transport;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public void setDays(Integer days) {
    this.days = days;
  }


  @Override
  public String toString() {
    return "\nId:" + id + " " + "Tourist Voucher.... " + " " + "Cost: " + cost + " "
      + "\n\t Hotel: " + hotel.numberOfStarts + "stars, " + hotel.meals + "meals type, " + hotel.numberOfRooms
      + "rooms, (optional: is TV included: " + hotel.isTvIncluded + " is Air Conditioning Included:, "
      + hotel.isAirConditioningIncluded + " "
      + "\n\t type: " + type + " "
      + "\n\t country: " + country + " "
      + "\n\t days: " + days + " "
      + "\n\t transport: " + transport + " ";

  }


  class Hotel {

    Integer numberOfStarts, numberOfRooms;
    Boolean isMealsIncluded, isTvIncluded, isAirConditioningIncluded;
    String meals;

    public void setNumberOfStarts(Integer numberOfStarts) {
      this.numberOfStarts = numberOfStarts;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
      this.numberOfRooms = numberOfRooms;
    }

    public void setIsMealsIncluded(Boolean isMealsIncluded) {
      this.isMealsIncluded = isMealsIncluded;
    }

    public void setIsTvIncluded(Boolean isTvIncluded) {
      this.isTvIncluded = isTvIncluded;
    }

    public void setIsAirConditioningIncluded(Boolean isAirConditioningIncluded) {
      this.isAirConditioningIncluded = isAirConditioningIncluded;
    }

    public void setMeals(String meals) {
      this.meals = meals;
    }

  }

}

