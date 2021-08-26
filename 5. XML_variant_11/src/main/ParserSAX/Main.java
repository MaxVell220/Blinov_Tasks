package main.ParserSAX;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.util.List;


public class Main {

  public static void main(String[] args) {
    try {
      SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
      DocHandler dh = new DocHandler();
      List v;
      parser.parse("Tourist_voucher.xml", dh);
      v = dh.getNotes();
			for (Object o : v) {
				System.out.println(((TouristVoucher) o).toString());
			}
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
