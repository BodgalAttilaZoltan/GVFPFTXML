package hu.domparse.GVFPFT;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Objects;

public class DOMQueryGVFPFT{
    public static void main(String[] args){
        try {
            //xml beolvasása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("XMLGVFPFT.xml");
            doc.getDocumentElement().normalize();

            // Melyik városban van a gyártók székhelye

            NodeList gyartok = doc.getElementsByTagName("gyarto");
            for (int i = 0; i < gyartok.getLength(); i++) {
                Element gyarto = (Element) gyartok.item(i);
                String nev = gyarto.getElementsByTagName("nev").item(0).getTextContent();
                Element cim = (Element) gyarto.getElementsByTagName("cim").item(0);
                String varos = cim.getElementsByTagName("varos").item(0).getTextContent();
                System.out.println("Gyártó neve: " + nev + ", Város: " + varos);
            }

            // Annak lekérdezése hogy mely nyelveken érhetőek el a játékok

            NodeList jatekok = doc.getElementsByTagName("jatek");
            for (int i = 0; i < jatekok.getLength(); i++) {
                Element jatek = (Element) jatekok.item(i);
                System.out.println("Játék ID: " + jatek.getAttribute("jid"));
                NodeList nyelvek = jatek.getElementsByTagName("elerhetonyelvek");
                for (int j = 0; j < nyelvek.getLength(); j++) {
                    String nyelv = nyelvek.item(j).getTextContent();
                    System.out.println("  Nyelv: " + nyelv);
                }
            }

            // 200-nál több euróval rendelkező felhasználók kiírása

            NodeList felhasznalok = doc.getElementsByTagName("felhasznalo");
            for (int i = 0; i < felhasznalok.getLength(); i++) {
                Element felhasznalo = (Element) felhasznalo.item(i);
                int penztarca = felhasznalo.getElementsByTagName("penztarca");

                if (penztarca > 200) {
                    System.out.println(" Felhasználó neve: " felhasznalo.getElementsByTagName("felhasznaloinev"));
                }
            }

            //

        }
    }
}