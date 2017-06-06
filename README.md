## Description:
Parses JUnit XML results into POJOs using JAXB.

Example:
```java
import org.openmbee.junit.JUnitMarshalling;
import org.openmbee.junit.model.JUnitTestSuite;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class JUnitParsingExample {
    public static void main(String... args) throws IOException, JAXBException, XMLStreamException {
        FileInputStream fileInputStream = new FileInputStream(Paths.get("/path/to/junit/report.xml").toFile());
        JUnitTestSuite testSuite = JUnitMarshalling.unmarshalTestSuite(fileInputStream);
    }
}
```# junit-xml-parser
