import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

class IObjectDataLoader {

    private ArrayList<Object> response = new ArrayList<Object>();

    void readFile(String fileName){

        try {
            File settingsFile = new File(fileName);
            DocumentBuilderFactory dbFactiry = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactiry.newDocumentBuilder();
            Document doc = dBuilder.parse(settingsFile);

            doc.getDocumentElement().normalize();

            NodeList nodeListColumn = doc.getElementsByTagName("EnumEnum");

            for (int i = 0; i < nodeListColumn.getLength(); i++){
                Node cNode = nodeListColumn.item(i);

                // Получение атрибутов первого(IObject) дочернего элемента
                Node at1 = cNode.getChildNodes().item(1).getAttributes().getNamedItem("UID");
                Node at2 = cNode.getChildNodes().item(1).getAttributes().getNamedItem("Name");
                Node at3 = cNode.getChildNodes().item(1).getAttributes().getNamedItem("Description");

                response.add(new IObject(at1.getTextContent(), at2.getTextContent()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ArrayList<Object> getResponse() {
        return response;
    }
}
