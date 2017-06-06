package org.openmbee.junit.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.XmlAttribute;

@Getter
@Setter
@Accessors(chain = true)
public class JUnitProperty {
    @XmlAttribute(required = true)
    private String name;
    @XmlAttribute(required = true)
    private String value;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
