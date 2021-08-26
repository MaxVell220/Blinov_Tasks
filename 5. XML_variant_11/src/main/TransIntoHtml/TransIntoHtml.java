package main.TransIntoHtml;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class TransIntoHtml {

  public static void main(String[] args) {

    try {
      TransformerFactory tFactory = TransformerFactory.newInstance();

      Source xslDoc = new StreamSource("Tourist_voucher.xsl");
      Source xmlDoc = new StreamSource("Tourist_voucher.xml");

      String outputFileName = "Tourist_voucher.html";

      OutputStream htmlFile = new FileOutputStream(outputFileName);
      Transformer transform = tFactory.newTransformer(xslDoc);
      transform.transform(xmlDoc, new StreamResult(htmlFile));

      System.out.println("Transformation your xsl to html file completed successfully!");
      System.out.println("You can find your html file in the workspace directory");
    } catch (FileNotFoundException | TransformerFactoryConfigurationError | TransformerException e) {
      e.printStackTrace();
    }
  }
}
