package hu.domparse.GVFPFT;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class DomReadGVFPFT{
    public static void main(String[] args){
        try{
            //xml beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLGVFPFT.xml");
            doc.getDocumentElement().normalize();

            // Adatok kiírása

            Element rootElement = doc.getDocumentElement();
            NodeList childNodes = rootElement.getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Tag: " + element.getTagName());

                    NamedNodeMap attributes = element.getAttributes();
                    for (int j = 0; j < attributes.getLength(); j++) {
                        Node attribute = attributes.item(j);
                        System.out.println("  Attribútum: " + attribute.getNodeName() + " = " + attribute.getNodeValue());
                    }

                    NodeList subElements = element.getChildNodes();
                    for (int k = 0; k < subElements.getLength(); k++) {
                        Node subNode = subElements.item(k);
                        if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println("    Al-tag: " + subNode.getNodeName() + " = " + subNode.getTextContent());
                        }
                    }
                }
            }

            // Fájlba való kiírás

            TransformerFactory transformerFactory = TransfromerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLGVFPFTread.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}