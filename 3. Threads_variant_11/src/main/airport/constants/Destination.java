package main.airport.constants;

public enum Destination {
  NEW_YORK("New York"),
  MADRID("Madrid"),
  MOSCOW("Moscow"),
  LONDON("London"),
  ROME("Rome");

  private final String  value;

  Destination(final String newValue) {
    value = newValue;
  }

  public String getValue() { return value; }
}
