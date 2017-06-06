package org.openmbee.junit.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@XmlRootElement(name = "testsuite")
public class JUnitTestSuite {
    @XmlAttribute
    private boolean disabled;
    @XmlAttribute
    private int errors;
    @XmlAttribute
    private int failures;
    @XmlAttribute
    private String hostname;
    @XmlAttribute
    private String id;
    @XmlAttribute(required = true)
    private String name;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @XmlAttribute(name = "package")
    private String pakkage;
    @XmlAttribute
    private int skipped;
    @XmlAttribute(required = true)
    private int tests;
    @XmlAttribute
    private double time;
    @XmlAttribute
    private String timestamp;

    @XmlElement(name = "property")
    @XmlElementWrapper(name = "properties")
    private List<JUnitProperty> properties;
    @XmlElement(name = "testcase")
    private List<JUnitTestCase> testCases;

    @XmlElement(name = "system-out")
    private String systemOut;
    @XmlElement(name = "system-err")
    private String systemErr;

    public String getPackage() {
        return pakkage;
    }

    public void setPackage(String pakkage) {
        this.pakkage = pakkage;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, "systemOut", "systemErr").replace("pakkage", "package");
    }
}
