import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParseConfigurationExcemption;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMReadGVFPFT {

    public static void main(String argv[]) throws SAXException, IOException, ParserConfiguration Exception {
 
        File xmlFile = new File("hallgatoGVFPFT.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        //A DocumentBuilderFactory-ból megkapjuk a DocumentBuildert.
        //A DocumentBuilder tartalmazza az API-t a DOM-dokumentum példányok XML-dokumentumból való beszerzését.

        Document doc = dBuilder.parse(xmlFile);
        //A parse() metódus elemzi az XML fájlt a Document.

        doc.getDocumentElement().normalize();
        //A dokumentum normalizálása segít a helyes eredmények elérésében.

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        //Megkapjuk a dokumentum gyökérelemét.

        NodeList nlist = doc.getElementsByTagName("hallgato");
        //A getElementsByTagName() metódus segítségével megkapjuk a user elem NodeListjét a dokumentumban.
        
        for (int i = 0; i < nList.getLength(); i++) { 
            //A listán for ciklussal megyünk végig.

            Node nNode = nlist.item(i);

            System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node. ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");
                //Az elem attribútumot a getAttribute() segítségével kapjuk meg.

                Node node1 = elem.getElementsByTagName("keresztnev").item(0);
                String fname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("vezeteknev").item(0);
                String Lname = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("foglalkozas").item(0);
                String pname = node3.getTextContent();
                //Megkapjuk a user elem három alelemének szöveges tartalmát.

                System.out.printf("User id: %s%n", uid);
                System.out.printf("First name: %s%n", fname);
                System.out.printf("Last name: %s%n", Lname);
                System.out.printf("Profession: %s%n", pname);
            }
        }
    }
}
