package hu.domparse.GVFPFT;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class DomWriteGVFPFT{
    public static void main(String[] args){
        try{
            //xml beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLGVFPFT.xml");
            doc.getDocumentElement().normalize();

            // Adatok kiírása

            Element rootElement = doc.getDocumentElement();
            printElement(rootElement, 0);



            // Fájlba való kiírás

            TransformerFactory transformerFactory = TransfromerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLGVFPFTwrite.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printElement(Element element, int indent) {
        // Behúzás létrehozása
        StringBuilder indentString = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            indentString.append("  ");
        }

        // Elem neve és attribútumai
        System.out.println(indentString + "<" + element.getTagName() + ">");
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            System.out.println(indentString + "  @" + attribute.getNodeName() + " = " + attribute.getNodeValue());
        }

        // Gyermekek kiíratása
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node child = childNodes.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                printElement((Element) child, indent + 1);
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                String textContent = child.getTextContent().trim();
                if (!textContent.isEmpty()) {
                    System.out.println(indentString + "  " + textContent);
                }
            }
        }

        System.out.println(indentString + "</" + element.getTagName() + ">");
    }
}