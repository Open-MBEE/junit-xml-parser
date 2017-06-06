package org.openmbee.junit.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class JUnitTestCase {
    @XmlAttribute
    private int assertions;
    @XmlAttribute(name = "classname", required = true)
    private String className;
    @XmlAttribute(required = true)
    private String name;
    @XmlAttribute
    private String status;
    @XmlAttribute
    private Double time;

    @XmlElement
    private JUnitSkipped skipped;
    @XmlElement(name = "error")
    private List<JUnitError> errors;
    @XmlElement(name = "failure")
    private List<JUnitFailure> failures;

    @XmlElement(name = "system-out")
    private String systemOut;
    @XmlElement(name = "system-err")
    private String systemErr;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, "systemOut", "systemErr");
    }
}
