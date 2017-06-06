package org.openmbee.junit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.openmbee.junit.model.JUnitTestSuite;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class JUnitMarshalling {
    private static final String systemOutStart = "<system-out>", systemOutEnd = "</system-out>";
    private static final String systemErrStart = "<system-err>", systemErrEnd = "</system-err>";

    public static JUnitTestSuite unmarshalTestSuite(InputStream stream) throws JAXBException, XMLStreamException, IOException {
        JAXBContext context = JAXBContext.newInstance(JUnitTestSuite.class);

        XMLInputFactory xif = XMLInputFactory.newFactory();
        XMLStreamReader xsr = xif.createXMLStreamReader(createEscapedJUnitInputStream(stream));
        xsr = xif.createFilteredReader(xsr, reader -> {
            if (reader.getEventType() == XMLStreamReader.CHARACTERS) {
                return reader.getText().trim().length() > 0;
            }
            return true;
        });

        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (JUnitTestSuite) unmarshaller.unmarshal(xsr);
    }

    public static InputStream createEscapedJUnitInputStream(InputStream stream) throws IOException {
        try {

            String contents = IOUtils.toString(stream, Charset.defaultCharset());

            int systemOutStartIndex = contents.indexOf(systemOutStart);
            int systemOutEndIndex = contents.indexOf(systemOutEnd);
            if (systemOutStartIndex > 0 && systemOutEndIndex > 0) {
                systemOutStartIndex += systemOutStart.length();
                contents = contents.substring(0, systemOutStartIndex) + StringEscapeUtils.escapeXml10(contents.substring(systemOutStartIndex, systemOutEndIndex)) + contents.substring(systemOutEndIndex, contents.length());
            }

            int systemErrStartIndex = contents.indexOf(systemErrStart);
            int systemErrEndIndex = contents.indexOf(systemErrEnd);
            if (systemErrStartIndex > 0 && systemErrEndIndex > 0) {
                systemErrStartIndex += systemErrStart.length();
                contents = contents.substring(0, systemErrStartIndex) + StringEscapeUtils.escapeXml10(contents.substring(systemErrStartIndex, systemErrEndIndex)) + contents.substring(systemErrEndIndex, contents.length());
            }

            return new ByteArrayInputStream(contents.getBytes());
        } finally {
            stream.close();
        }
    }
}
