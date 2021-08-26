package main.ParserDOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;


public class Dom {

  static int i = -1, j = -1, k = -1;

  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    String type = "", country = "", transport = "", meals = "";
    int days = 0, numberOfStarts = 0, numberOfRooms = 0;
    double cost = 0.0;
    boolean isMealsIncluded = false, isTvIncluded = false, isAirConditioningIncluded = false;

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = factory.newDocumentBuilder();
    Document doc = documentBuilder.parse(new File("tourist_voucher.xml"));

    NodeList nodeList1 = doc.getElementsByTagName("tourist_voucher");
    NodeList nodeList2 = doc.getElementsByTagName("hotel");

    System.out.println(
      "type   country 	days  	transport 	cost  	numberOfStarts  	isMealsIncluded 	meals  numberOfRooms  isTvIncluded isAirConditioningIncluded");

    while (i < nodeList1.getLength() - 1) {
      i++;
      Element element1 = (Element) nodeList1.item(i);

      type = element1.getElementsByTagName("type").item(0).getChildNodes().item(0).getNodeValue();
      country = (element1.getElementsByTagName("country").item(0).getChildNodes().item(0).getNodeValue());
      days =
        Integer.parseInt(element1.getElementsByTagName("days").item(0).getChildNodes().item(0).getNodeValue());
      transport =
        (element1.getElementsByTagName("transport").item(0).getChildNodes().item(0).getNodeValue());
      cost =
        Double.parseDouble(element1.getElementsByTagName("cost").item(0).getChildNodes().item(0).getNodeValue());
      if (j < nodeList2.getLength()) {
        j++;
        Element element2 = (Element) nodeList2.item(j);
        numberOfStarts = Integer
          .parseInt(element2.getElementsByTagName("numberOfStarts").item(0).getChildNodes().item(0).getNodeValue());
        isMealsIncluded = Boolean
          .parseBoolean(
            element2.getElementsByTagName("isMealsIncluded").item(0).getChildNodes().item(0).getNodeValue());
        meals = (element2.getElementsByTagName("meals").item(0).getChildNodes().item(0).getNodeValue());
        numberOfRooms = Integer
          .parseInt(element2.getElementsByTagName("numberOfRooms").item(0).getChildNodes().item(0).getNodeValue());
        isTvIncluded = Boolean
          .parseBoolean(element2.getElementsByTagName("isTvIncluded").item(0).getChildNodes().item(0).getNodeValue());
        isAirConditioningIncluded = Boolean
          .parseBoolean(
            element2.getElementsByTagName("isAirConditioningIncluded").item(0).getChildNodes().item(0).getNodeValue());
      }

      System.out.println(
        type + "	" + country + "	" + days + "		" + transport + "		" + cost + "		"
          + numberOfStarts + "		" + isMealsIncluded + "			" + meals + "		" + numberOfRooms + "			"
          + isTvIncluded + "		" + isAirConditioningIncluded);

    }
  }
}


	

