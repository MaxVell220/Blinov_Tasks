package main.ParserSAX;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class DocHandler extends DefaultHandler implements ConstNote {

  List notes = new ArrayList();
  TouristVoucher curr = new TouristVoucher();
  int current = -1;

  public List getNotes() {
    return notes;
  }

  @Override
  public void startDocument() {
    System.out.println("parsing started");
  }

  @Override
  public void endDocument() {
    System.out.println("parsing ended");
  }

  @Override
  public void startElement(String uri, String localName, String qName,
    Attributes attrs) {
    if (qName.equals("TouristVoucher")) {
      curr = new TouristVoucher();
      curr.setId(attrs.getValue(0));
    }
    if (qName.equals("type")) {
      current = TYPE;
    } else if (qName.equals("country")) {
      current = COUNTRY;
    } else if (qName.equals("days")) {
      current = DAYS;
    } else if (qName.equals("transport")) {
      current = TRANSPORT;
    } else if (qName.equals("cost")) {
      current = COST;
    } else if (qName.equals("numberOfStarts")) {
      current = NUMBER_OF_STARS;
    } else if (qName.equals("isMealsIncluded")) {
      current = IS_MEALS_INCLUDED;
    } else if (qName.equals("meals")) {
      current = MEALS;
    } else if (qName.equals("numberOfRooms")) {
      current = NUMBER_OF_ROOMS;
    } else if (qName.equals("isTvIncluded")) {
      current = IS_TV_INCLUDED;
    } else if (qName.equals("isAirConditioningIncluded")) {
      current = IS_AIR_CONDITIONING_INCLUDED;
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    if (qName.equals("tourist_voucher")) {
      notes.add(curr);
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) {
    String s = new String(ch, start, length);
    try {
      switch (current) {
        case TYPE:
          curr.setType(s);
          break;
        case COUNTRY:
          curr.setCountry(s);
          break;
        case DAYS:
          curr.setDays(Integer.parseInt(s));
          break;
        case TRANSPORT:
          curr.setTransport(s);
          break;
        case COST:
          curr.setCost(Double.parseDouble(s));
          break;
        case NUMBER_OF_STARS:
          curr.hotel.setNumberOfStarts(Integer.parseInt(s));
          break;
        case IS_MEALS_INCLUDED:
          curr.hotel.setIsMealsIncluded(Boolean.parseBoolean(s));
          break;
        case MEALS:
          curr.hotel.setMeals(s);
          break;
        case NUMBER_OF_ROOMS:
          curr.hotel.setNumberOfRooms(Integer.parseInt(s));
          break;
        case IS_TV_INCLUDED:
          curr.hotel.setIsTvIncluded(Boolean.parseBoolean(s));
          break;
        case IS_AIR_CONDITIONING_INCLUDED:
          curr.hotel.setIsAirConditioningIncluded(Boolean.parseBoolean(s));
          break;
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    current = -1;
  }
}