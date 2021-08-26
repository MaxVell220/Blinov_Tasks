package main.airport.constants;

public enum Distance {
  LOW(200),
  MEDIUM(500),
  HIGH(700);

  private final int value;

  Distance(final int newValue) {
    value = newValue;
  }

  public int getValue() { return value; }
}
